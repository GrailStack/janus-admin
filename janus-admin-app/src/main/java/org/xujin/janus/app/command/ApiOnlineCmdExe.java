package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApiOnlineCmd;
import org.xujin.janus.domain.api.constant.ApiOperateTypeEnum;
import org.xujin.janus.domain.api.constant.ApiStatusEnum;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.entity.ApiReleaseE;
import org.xujin.janus.domain.api.factory.ApiFactory;
import org.xujin.janus.domain.api.service.OutServiceConfigService;
import org.xujin.janus.infrastructure.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 14:10
 **/
@CmdHandler
public class ApiOnlineCmdExe implements CommandExecutorI<ResultData<Boolean>, ApiOnlineCmd> {

    @Autowired
    private ApiFactory apiFactory;

    @Autowired
    private OutServiceConfigService outServiceConfigService;

    @Override
    @Transactional
    public ResultData<Boolean> execute(ApiOnlineCmd cmd) {
        ApiReleaseE releaseE = apiFactory.createApiReleaseE(cmd.getId());
        if (releaseE == null) {
            throw new BusinessException("400", "Api不存在");
        }
        if (releaseE.isOnline()) {
            throw new BusinessException("500", "该Api已处于在线状态");
        }
        if (releaseE.isLocked()) {
            throw new BusinessException("500", "该Api在操作中，请稍后重试");
        }
        if (ApiStatusEnum.ONLINE.equals(releaseE.getStatus())) {
            throw new BusinessException("500", "该Api已处于在线状态");
        }
        if (!releaseE.lock(ApiOperateTypeEnum.ONLINE.getCode())) {
            throw new BusinessException("500", "该Api在操作中，请稍后重试");
        }
        ApiDraftE draftE = createNewDraftE(releaseE);
        draftE.save();

        outServiceConfigService.copyFromReleaseToDraft(releaseE.getClusterCode(), releaseE.getId(), draftE.getId());

        return ResultData.success(true);
    }

    private ApiDraftE createNewDraftE(ApiReleaseE releaseE) {
        ApiDraftE draftE = apiFactory.createApiDraftE(releaseE);
        draftE.setCreateUser(UserUtil.getCurrUser());
        draftE.setOperateType(ApiOperateTypeEnum.ONLINE);
        return draftE;
    }

}
