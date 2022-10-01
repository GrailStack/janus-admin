package org.xujin.janus.app.command;

import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApiReleaseDetailQueryCmd;
import org.xujin.janus.app.command.co.ApiReleaseCO;
import org.xujin.janus.app.converter.ApiClientConverter;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.domain.user.service.UserService;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.OutServiceReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;
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
public class ApiReleaseDetailQueryCmdExe implements CommandExecutorI<ResultData<ApiReleaseCO>, ApiReleaseDetailQueryCmd> {

    @Autowired
    private ApiReleaseMapper apiReleaseMapper;

    @Autowired
    private OutServiceReleaseMapper outServiceReleaseMapper;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiClientConverter converter;

    @Override
    public ResultData<ApiReleaseCO> execute(ApiReleaseDetailQueryCmd cmd) {
        ApiReleaseDO releaseDO = apiReleaseMapper.selectById(cmd.getId());
        if (releaseDO == null) {
            throw new BusinessException("400", "记录不存在");
        }
        List<OutServiceReleaseDO> outServiceReleaseDOList = outServiceReleaseMapper.findByApiReleaseId(releaseDO.getId());
        ApiReleaseCO detailCO = assemble(releaseDO, outServiceReleaseDOList);
        return ResultData.success(detailCO);
    }

    private ApiReleaseCO assemble(ApiReleaseDO releaseDO, List<OutServiceReleaseDO> outServiceReleaseDOList) {
        ApiReleaseCO apiReleaseCO = converter.convertToApiReleaseCO(releaseDO, outServiceReleaseDOList);

        String clusterName = clusterService.findClusterName(releaseDO.getClusterCode());
        apiReleaseCO.setClusterName(clusterName);

        Map<String, String> userDisplayNames = userService.getNames(Lists.newArrayList(releaseDO.getCreateUser(), releaseDO.getUpdateUser()));
        apiReleaseCO.setCreateUserName(userDisplayNames.get(releaseDO.getCreateUser()));
        apiReleaseCO.setUpdateUserName(userDisplayNames.get(releaseDO.getUpdateUser()));

        apiReleaseCO.setParamTransType(releaseDO.getParamTransType());
        apiReleaseCO.setParamTransConfig(releaseDO.getParamTransConfig());

        return apiReleaseCO;
    }

}
