package org.xujin.janus.controller;

import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddApplyCmd;
import org.xujin.janus.app.command.cmo.ApplyAuditCmd;
import org.xujin.janus.app.command.cmo.DeleteApplyCmd;
import org.xujin.janus.app.command.cmo.UpdateApplyCmd;
import org.xujin.janus.app.command.cmo.query.PageApplyCmd;
import org.xujin.janus.app.command.cmo.query.QueryApplyDetailCmd;
import org.xujin.janus.app.command.co.ApplyDetailCO;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:53
 * @desc
 */
@RestController
@RequestMapping("/admin/apply")
@Api("审批管理")
public class ApplyController {
    @Autowired
    protected CommandBus commandBus;

    @PostMapping("/add")
    @ApiOperation(value = "增加审批")
    public ResultData<Void> add(@RequestBody AddApplyCmd addApplyCmd) {
        return commandBus.send(addApplyCmd);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除审批")
    public ResultData delete(@RequestBody DeleteApplyCmd deleteApplyCmd) {
        deleteApplyCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(deleteApplyCmd);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新审批")
    public ResultData update(@RequestBody UpdateApplyCmd updateApplyCmd) {
        updateApplyCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(updateApplyCmd);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询审批详情")
    public ResultData<ApplyDetailCO> detail(@ModelAttribute QueryApplyDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/page")
    @ApiOperation(value = "查询审批分页")
    public ResultData<PageResult<ApplyDetailCO>> page(@ModelAttribute @Valid PageApplyCmd pageApplyCmd) {
        return commandBus.send(pageApplyCmd);
    }

    @PostMapping("/audit")
    @ApiOperation(value = "审批变更申请")
    public ResultData<Boolean> auditApply(@RequestBody ApplyAuditCmd cmd) {
        return commandBus.send(cmd);
    }

}
