package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.UpdateApplyCmd;
import org.xujin.janus.app.converter.ApplyClientConverter;
import org.xujin.janus.domain.user.entity.ApplyE;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:22
 * @desc
 */
@Slf4j
@CmdHandler
public class UpdateApplyCmdExe implements CommandExecutorI<ResultData, UpdateApplyCmd> {
    @Resource
    private ApplyClientConverter clientConverter;

    @Override
    public ResultData execute(UpdateApplyCmd cmd) {
        ResultData resultData = ResultData.success(null);
        ApplyE applyE = clientConverter.updateCmdToEntity(cmd);
        applyE.update();
        return resultData;
    }

    protected boolean check(UpdateApplyCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"审批id不能为空");
        return true;
    }
}
