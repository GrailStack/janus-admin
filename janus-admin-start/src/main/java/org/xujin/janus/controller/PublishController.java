package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.app.command.cmo.query.PagePublishCmd;
import org.xujin.janus.app.command.cmo.query.QueryPublishDetailCmd;
import org.xujin.janus.app.command.co.PublishDetailCO;

import javax.validation.Valid;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:53
 * @desc
 */
@RestController
@RequestMapping("/admin/publish")
@Api("下发管理")
public class PublishController {
    @Autowired
    protected CommandBus commandBus;

    @PostMapping("/add")
    @ApiOperation(value = "增加下发")
    public ResultData<Void> add(@RequestBody AddPublishCmd addPublishCmd) {
        return commandBus.send(addPublishCmd);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询下发详情")
    public ResultData<PublishDetailCO> detail(@ModelAttribute QueryPublishDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @PostMapping("/page")
    @ApiOperation(value = "查询下发分页")
    public ResultData<PageResult<PublishDetailCO>> page(@RequestBody @Valid PagePublishCmd pagePublishCmd) {
        return commandBus.send(pagePublishCmd);
    }

    @PostMapping("/pullDownServer")
    @ApiOperation(value = "拉出服务")
    public ResultData<Boolean> pullDownServer(@RequestBody @Valid ServerPullDownCmd cmd) {
        return commandBus.send(cmd);
    }

    @PostMapping("/publishToServer")
    @ApiOperation(value = "下发到server")
    public ResultData<Boolean> publishToServer(@RequestBody @Valid ChangePublishToServerCmd cmd) {
        return commandBus.send(cmd);
    }

    @PostMapping("/fullPublishToServer")
    @ApiOperation(value = "全量下发到server")
    public ResultData<Boolean> fullPublishToServer(@RequestBody @Valid ChangeFullPublishToServerCmd cmd) {
        return commandBus.send(cmd);
    }

    @PostMapping("/overPublish")
    @ApiOperation(value = "结束下发到server")
    public ResultData<Boolean> overPublish(@RequestBody @Valid ChangePublishOverCmd cmd) {
        return commandBus.send(cmd);
    }

    @PostMapping("/completePublish")
    @ApiOperation(value = "完成下发")
    public ResultData<Boolean> completePublish(@RequestBody @Valid ChangeReleaseCompleteCmd cmd) {
        return commandBus.send(cmd);
    }

/*
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
    public ResultData<PageResult<ApplyDetailCO>> page(@ModelAttribute PageApplyCmd pageApplyCmd) {
        return commandBus.send(pageApplyCmd);
    }
*/

}
