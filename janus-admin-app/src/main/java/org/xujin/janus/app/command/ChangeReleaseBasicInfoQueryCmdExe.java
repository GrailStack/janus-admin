package org.xujin.janus.app.command;

import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ChangeReleaseBasicInfoQueryCmd;
import org.xujin.janus.app.command.co.ChangeReleaseBasicInfoCO;
import org.xujin.janus.app.service.ClusterAppService;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApplyMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishIpMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishIpDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/16 16:57
 **/
@CmdHandler
public class ChangeReleaseBasicInfoQueryCmdExe implements CommandExecutorI<ResultData<ChangeReleaseBasicInfoCO>, ChangeReleaseBasicInfoQueryCmd> {

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ClusterAppService clusterAppService;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private PublishIpMapper publishIpMapper;


    @Override
    public ResultData<ChangeReleaseBasicInfoCO> execute(ChangeReleaseBasicInfoQueryCmd cmd) {
        ApplyDO applyDO = applyMapper.selectById(cmd.getApplyId());
        if (applyDO == null) {
            throw new BusinessException("400", "变更申请不存在");
        }
        PublishDO publishDO = publishMapper.findByApplyId(applyDO.getId());
        ChangeReleaseBasicInfoCO co = convert(applyDO, publishDO);

        List<String> ipList = clusterAppService.findClusterInstanceHosts(applyDO.getClusterCode());
        co.setIpList(ipList);
        co.setSelectedIpList(getSelectedIpList(publishDO));

        return ResultData.success(co);
    }

    private List<String> getSelectedIpList(PublishDO publishDO) {
        if (publishDO == null) {
            return Lists.newArrayList();
        }
        List<PublishIpDO> publishIpDOList = publishIpMapper.findByPublishIdAndType(publishDO.getId(), publishDO.getType().getCode());
        return publishIpDOList.stream().map(PublishIpDO::getIp).collect(Collectors.toList());
    }

    private ChangeReleaseBasicInfoCO convert(ApplyDO applyDo, PublishDO publishDO) {
        ChangeReleaseBasicInfoCO co = new ChangeReleaseBasicInfoCO();
        co.setApplyId(applyDo.getId());
        co.setClusterCode(applyDo.getClusterCode());
        co.setStatus(applyDo.getStatus());
        co.setCreator(applyDo.getCreator());
        co.setApprover(applyDo.getApprover());
        co.setDescription(applyDo.getDescription());
        if (publishDO != null) {
            co.setType(publishDO.getType());
        }
        return co;
    }

}
