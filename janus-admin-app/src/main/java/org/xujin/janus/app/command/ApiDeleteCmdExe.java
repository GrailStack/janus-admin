package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApiDeleteCmd;
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
 * @date 2020/6/1 21:05
 **/
@CmdHandler
public class ApiDeleteCmdExe implements CommandExecutorI<ResultData<Boolean>, ApiDeleteCmd> {

    @Autowired
    private ApiFactory apiFactory;

    @Autowired
    private OutServiceConfigService outServiceConfigService;

    @Override
    @Transactional
    public ResultData<Boolean> execute(ApiDeleteCmd cmd) {
        ApiReleaseE releaseE = apiFactory.createApiReleaseE(cmd.getId());
        if (releaseE == null) {
            throw new BusinessException("400", "Api不存在");
        }
        if (releaseE.isLocked()) {
            throw new BusinessException("500", "该Api在操作中，请稍后重试");
        }
        if (ApiStatusEnum.ONLINE.equals(releaseE.getStatus())) {
            throw new BusinessException("500", "该Api处于在线状态，请先下线后重试");
        }
        if (!releaseE.lock(ApiOperateTypeEnum.DELETE.getCode())) {
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
        draftE.setOperateType(ApiOperateTypeEnum.DELETE);
        return draftE;
    }

}
