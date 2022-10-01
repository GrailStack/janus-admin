package org.xujin.janus.app.command;

import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApiDraftDetailQueryCmd;
import org.xujin.janus.app.command.co.ApiDraftCO;
import org.xujin.janus.app.converter.ApiClientConverter;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.domain.user.service.UserService;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiDraftMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.OutServiceDraftMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiDraftDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceDraftDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/8 11:41
 **/
@CmdHandler
public class ApiDraftDetailQueryCmdExe implements CommandExecutorI<ResultData<ApiDraftCO>, ApiDraftDetailQueryCmd> {

    @Autowired
    private ApiDraftMapper apiDraftMapper;

    @Autowired
    private OutServiceDraftMapper outServiceDraftMapper;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiClientConverter converter;


    @Override
    public ResultData<ApiDraftCO> execute(ApiDraftDetailQueryCmd cmd) {
        ApiDraftDO draftDO = apiDraftMapper.selectById(cmd.getId());
        if (draftDO == null) {
            throw new BusinessException("400", "记录不存在");
        }
        List<OutServiceDraftDO> outServiceDraftDOList = outServiceDraftMapper.findByApiDraftId(draftDO.getId());
        ApiDraftCO draftCO = assemble(draftDO, outServiceDraftDOList);
        return ResultData.success(draftCO);
    }

    private ApiDraftCO assemble(ApiDraftDO releaseDO, List<OutServiceDraftDO> outServiceReleaseDOList) {
        ApiDraftCO apiDraftCO = converter.convertToApiDraftCO(releaseDO, outServiceReleaseDOList);

        String clusterName = clusterService.findClusterName(releaseDO.getClusterCode());
        apiDraftCO.setClusterName(clusterName);

        Map<String, String> userDisplayNames = userService.getNames(Lists.newArrayList(releaseDO.getCreateUser(), releaseDO.getUpdateUser()));
        apiDraftCO.setCreateUserName(userDisplayNames.get(releaseDO.getCreateUser()));
        apiDraftCO.setUpdateUserName(userDisplayNames.get(releaseDO.getUpdateUser()));

        apiDraftCO.setParamTransType(releaseDO.getParamTransType());
        apiDraftCO.setParamTransConfig(releaseDO.getParamTransConfig());

        return apiDraftCO;
    }

}
