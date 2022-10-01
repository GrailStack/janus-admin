package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.DeleteApplyCmd;
import org.xujin.janus.app.converter.ApplyClientConverter;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.ports.ApplyPort;
import org.xujin.janus.domain.user.service.ApplyService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;


/**
 * @author chenglong.ren
 */
@Slf4j
@CmdHandler
public class DeleteApplyCmdExe implements CommandExecutorI<ResultData, DeleteApplyCmd> {

    @Resource
    ApplyClientConverter converter;
    @Resource
    ApplyService applyService;

    @Override
    public ResultData execute(DeleteApplyCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        if (null == applyService.findById(cmd.getId().longValue())) {
            throw new BusinessException("400", "该审批不存在");
        }
        ApplyE applyE = converter.deleteCmdToEntity(cmd);
        applyE.delete();
        return resultData;
    }

    protected boolean check(DeleteApplyCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId() != null, "审批id不能为空");
        return true;
    }
}