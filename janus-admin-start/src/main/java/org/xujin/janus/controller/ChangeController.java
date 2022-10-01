package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.app.command.co.ChangeCollectionCO;
import org.xujin.janus.app.command.co.ChangeReleaseBasicInfoCO;
import org.xujin.janus.app.command.co.ChangeReleaseDetailCO;

import javax.validation.Valid;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 19:42
 **/
@Api
@RestController
@RequestMapping("/admin/change")
public class ChangeController {

    @Autowired
    private CommandBus commandBus;

    @ApiOperation("查询所有变更")
    @PostMapping("/collectChanges")
    public ResultData<ChangeCollectionCO> collectChanges(@RequestBody @Valid ChangeCollectCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("提交变更")
    @PostMapping("/submitChanges")
    public ResultData<Boolean> submitChanges(@RequestBody @Valid ChangeSubmitCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("查询变更详情")
    @PostMapping("/changeDetail")
    public ResultData<ChangeCollectionCO> queryChangeDetail(@RequestBody @Valid ChangeDetailQueryCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("下发变更")
    @PostMapping("/releaseChanges")
    public ResultData<Boolean> queryChangeReleaseBasicInfo(@RequestBody @Valid ChangeReleaseCmd cmd) {
        return commandBus.send(cmd);
    }


    @ApiOperation("查询变更下发详情")
    @PostMapping("/changeReleaseDetail")
    public ResultData<ChangeReleaseDetailCO> queryChangeReleaseDetail(@RequestBody @Valid ChangeReleaseDetailQueryCmd cmd) {
        return commandBus.send(cmd);
    }

    @ApiOperation("查询变更下发基础信息")
    @PostMapping("/changeReleaseBasicInfo")
    public ResultData<ChangeReleaseBasicInfoCO> queryChangeReleaseBasicInfo(@RequestBody @Valid ChangeReleaseBasicInfoQueryCmd cmd) {
        return commandBus.send(cmd);
    }

}
