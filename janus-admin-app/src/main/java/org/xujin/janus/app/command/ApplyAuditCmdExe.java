package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApplyAuditCmd;
import org.xujin.janus.domain.change.ChangeManager;
import org.xujin.janus.domain.change.audit.AuditChangesRequest;
import org.xujin.janus.domain.lock.ResourceLockService;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 13:51
 **/
@CmdHandler
public class ApplyAuditCmdExe implements CommandExecutorI<ResultData<Boolean>, ApplyAuditCmd> {

    @Autowired
    private ChangeManager changeManager;

    @Autowired
    private ApplyFactory applyFactory;

    @Autowired
    private ResourceLockService lockService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultData<Boolean> execute(ApplyAuditCmd cmd) {
        ApplyE applyE = applyFactory.createApplyE(cmd.getApplyId());
        if (applyE == null) {
            throw new BusinessException("400", "记录不存在");
        }
        if (!applyE.canAudit()) {
            throw new BusinessException("400", "记录当前状态不支持该操作");
        }
        if (!applyE.audit(cmd.getAction(), SessionUtils.getUserName())) {
            throw new BusinessException("400", "操作失败");
        }

        AuditChangesRequest auditRequest = new AuditChangesRequest();
        auditRequest.setApplyId(applyE.getId());
        auditRequest.setAction(cmd.getAction());
        changeManager.auditChanges(auditRequest);

        lockService.unLockCluster(applyE.getClusterCode());

        return ResultData.success(true);
    }

}
