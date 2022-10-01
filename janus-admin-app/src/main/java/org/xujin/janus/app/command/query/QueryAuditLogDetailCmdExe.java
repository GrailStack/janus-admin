package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryAuditLogDetailCmd;
import org.xujin.janus.app.command.co.AuditLogDetailCO;
import org.xujin.janus.app.converter.AuditLogClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.AuditLogMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AuditLogDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:56
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryAuditLogDetailCmdExe implements CommandExecutorI<ResultData<AuditLogDetailCO>, QueryAuditLogDetailCmd> {

    @Resource
    private AuditLogMapper auditLogMapper;

    @Resource
    private AuditLogClientConverter converter;

    @Override
    public ResultData<AuditLogDetailCO> execute(QueryAuditLogDetailCmd cmd) {
        ResultData<AuditLogDetailCO> resultData = ResultData.success(null);
        check(cmd);
        resultData.setData(converter.dataToClient(detail(cmd)));
        return resultData;
    }

    protected boolean check(QueryAuditLogDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"id不能为空");
        return true;
    }

    public AuditLogDO detail(QueryAuditLogDetailCmd cmd) {
        return auditLogMapper.selectById(cmd.getId());
    }

}
