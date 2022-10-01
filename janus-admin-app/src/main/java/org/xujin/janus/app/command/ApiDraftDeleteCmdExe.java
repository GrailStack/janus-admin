package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApiDraftDeleteCmd;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.entity.ApiReleaseE;
import org.xujin.janus.domain.api.factory.ApiFactory;
import org.xujin.janus.infrastructure.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 13:47
 **/
@Slf4j
@CmdHandler
public class ApiDraftDeleteCmdExe implements CommandExecutorI<ResultData<Boolean>, ApiDraftDeleteCmd> {

    @Autowired
    private ApiFactory apiFactory;

    @Override
    @Transactional
    public ResultData<Boolean> execute(ApiDraftDeleteCmd cmd) {
        ApiDraftE draftE = apiFactory.createApiDraftE(cmd.getId());
        if (draftE == null) {
            throw new BusinessException("400", "记录不存在");
        }
        if (!draftE.canDelete()) {
            throw new BusinessException("400", String.format("当前状态为\"%s\"，无法删除", draftE.getStatus().getDesc()));
        }

        releaseLock(draftE);

        boolean result = draftE.delete();
        if (!result) {
            throw new BusinessException("500", "删除失败");
        }
        return ResultData.success(true);
    }

    private ApiReleaseE createReleaseE(ApiDraftE draftE) {
        ApiReleaseE releaseE = apiFactory.createApiReleaseE(draftE);
        releaseE.setUpdateUser(UserUtil.getCurrUser());
        return releaseE;
    }

    private void releaseLock(ApiDraftE draftE) {
        if (draftE.isNewApi()) {
            return;
        }
        ApiReleaseE releaseE = createReleaseE(draftE);
        boolean result = releaseE.releaseLock(draftE.getOperateType().getCode());
        if (!result) {
            throw new BusinessException("500", "释放锁失败，无法删除");
        }
    }

}
