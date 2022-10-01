package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;
import org.xujin.halo.annotation.command.CmdHandler;

import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddApplyCmd;
import org.xujin.janus.app.converter.ApplyClientConverter;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.ApplyE;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:55
 * @desc
 */
@CmdHandler
public class AddApplyCmdExe implements CommandExecutorI<ResultData, AddApplyCmd> {

    @Resource
    private ApplyClientConverter applyClientConverter;

    @Override
    public ResultData execute(AddApplyCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        ApplyE applyE = applyClientConverter.addCmdToEntity(cmd);
        applyE.setStatus(ApplyStatusEnum.WAIT_AUDIT);
        applyE.setCreator(cmd.getCurrentUserId());
        applyE.save();
        return resultData;
    }

    protected boolean check(AddApplyCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        return true;
    }
}
