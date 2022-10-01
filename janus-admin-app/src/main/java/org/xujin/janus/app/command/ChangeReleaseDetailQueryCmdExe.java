package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ChangeReleaseDetailQueryCmd;
import org.xujin.janus.app.command.co.ChangeReleaseDetailCO;
import org.xujin.janus.app.converter.PublishIpClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishIpMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishIpDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/16 10:42
 **/
@CmdHandler
public class ChangeReleaseDetailQueryCmdExe implements CommandExecutorI<ResultData<ChangeReleaseDetailCO>, ChangeReleaseDetailQueryCmd> {

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private PublishIpMapper publishIpMapper;

    @Autowired
    private PublishIpClientConverter converter;

    @Override
    public ResultData<ChangeReleaseDetailCO> execute(ChangeReleaseDetailQueryCmd cmd) {
        PublishDO publishDO = publishMapper.findByApplyId(cmd.getApplyId());
        if (publishDO == null) {
            throw new BusinessException("400", "该变更申请无发布记录");
        }

        ChangeReleaseDetailCO co = new ChangeReleaseDetailCO();
        co.setApplyId(cmd.getApplyId());
        co.setPublishId(publishDO.getId());

        List<PublishIpDO> publishIpDOList = publishIpMapper.findByPublishIdAndType(publishDO.getId(), cmd.getType().getCode());
        co.setItems(converter.dataToClients(publishIpDOList));

        return ResultData.success(co);
    }

}
