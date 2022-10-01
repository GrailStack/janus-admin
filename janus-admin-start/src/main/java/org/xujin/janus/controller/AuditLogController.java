package org.xujin.janus.controller;

import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.DeleteAuditLogCmd;
import org.xujin.janus.app.command.cmo.DeleteClusterCmd;
import org.xujin.janus.app.command.cmo.query.PageApplyCmd;
import org.xujin.janus.app.command.cmo.query.PageAuditLogCmd;
import org.xujin.janus.app.command.cmo.query.QueryApplyDetailCmd;
import org.xujin.janus.app.command.cmo.query.QueryAuditLogDetailCmd;
import org.xujin.janus.app.command.co.ApplyDetailCO;
import org.xujin.janus.app.command.co.AuditLogDetailCO;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/6/4 17:14
 * @desc
 */
@RestController
@RequestMapping("/admin/auditLog")
@Api("审计日志管理")
public class AuditLogController {
    @Resource
    private CommandBus commandBus;

    @GetMapping("/detail")
    @ApiOperation(value = "查询审批详情")
    public ResultData<AuditLogDetailCO> detail(@ModelAttribute QueryAuditLogDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/page")
    @ApiOperation(value = "查询审计日志分页")
    public ResultData<PageResult<AuditLogDetailCO>> page(@ModelAttribute PageAuditLogCmd pageAuditLogCmd) {
        return commandBus.send(pageAuditLogCmd);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除审计日志")
    public ResultData delete(@RequestBody DeleteAuditLogCmd deleteAuditLogCmd) {
        return commandBus.send(deleteAuditLogCmd);
    }


}
