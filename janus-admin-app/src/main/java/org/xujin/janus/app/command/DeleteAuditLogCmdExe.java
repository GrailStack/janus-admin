package org.xujin.janus.app.command;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.DeleteAuditLogCmd;
import org.xujin.janus.domain.user.entity.ClusterConfigE;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.AuditLogMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterConfigMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/27 10:02
 * @desc
 */
@CmdHandler
public class DeleteAuditLogCmdExe implements CommandExecutorI<ResultData, DeleteAuditLogCmd> {

    @Resource
    private AuditLogMapper auditLogMapper;

    @Override
    public ResultData execute(DeleteAuditLogCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        batchDeleteAuditLogs(cmd.getIds());
        return resultData;
    }


    protected boolean check(DeleteAuditLogCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getIds(), "审计日志id不能为null");
        return true;
    }

    public void batchDeleteAuditLogs(List<Integer> ids) {
        auditLogMapper.deleteBatchIds(ids);
    }
}
