package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.app.command.cmo.query.QueryApiCountCmd;
import org.xujin.janus.app.command.co.ApiDraftCO;
import org.xujin.janus.app.command.co.ApiReleaseCO;

import javax.validation.Valid;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/3 21:01
 **/
@Api
@RestController
@RequestMapping("/admin/api")
public class ApiController {

    @Autowired
    private CommandBus commandBus;

    @ApiOperation("分页查询Api")
    @PostMapping("/pageQuery")
    public ResultData<PageResult<ApiReleaseCO>> pageQueryApi(@RequestBody @Valid ApiReleasePageQueryCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("查询Api详情")
    @PostMapping("/queryDetail")
    public ResultData<PageResult<ApiReleaseCO>> queryDetail(@RequestBody @Valid ApiReleaseDetailQueryCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("分页查询Api草稿")
    @PostMapping("/pageQueryDraft")
    public ResultData<PageResult<ApiDraftCO>> pageQueryApiDraft(@RequestBody @Valid ApiDraftPageQueryCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("查询Api草稿详情")
    @PostMapping("/queryDetailDraft")
    public ResultData<PageResult<ApiDraftCO>> queryDetailDraft(@RequestBody @Valid ApiDraftDetailQueryCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("创建Api")
    @PostMapping("/create")
    public ResultData<Long> createApi(@RequestBody @Valid ApiCreateCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("编辑Api")
    @PostMapping("/edit")
    public ResultData<Boolean> editApi(@RequestBody @Valid ApiEditCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("上线Api")
    @PostMapping("/online")
    public ResultData<Boolean> onlineApi(@RequestBody @Valid ApiOnlineCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("下线Api")
    @PostMapping("/offline")
    public ResultData<Boolean> offlineApi(@RequestBody @Valid ApiOfflineCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("删除Api")
    @PostMapping("/delete")
    public ResultData<Boolean> deleteApi(@RequestBody @Valid ApiDeleteCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("编辑Api草稿")
    @PostMapping("/editDraft")
    public ResultData<Boolean> editApiDraft(@RequestBody @Valid ApiDraftEditCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("删除Api草稿")
    @PostMapping("/deleteDraft")
    public ResultData<Boolean> deleteApiDraft(@RequestBody @Valid ApiDraftDeleteCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/countApi")
    @ApiOperation(value = "api数量")
    public ResultData<Integer> countApi() {
        return commandBus.send(new QueryApiCountCmd());
    }

}
