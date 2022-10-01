package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddApiFilterCmd;
import org.xujin.janus.app.command.cmo.UpdateApiFilterCmd;
import org.xujin.janus.app.command.cmo.query.ApiFilterDetailCmd;
import org.xujin.janus.app.command.cmo.query.ListApiFilterCmd;
import org.xujin.janus.app.command.co.ApiFilterDetailCO;

import java.util.Map;

/**
 * @author chenglong.ren
 * @date 2020/6/17 11:44
 * @desc
 */
@Api
@RestController
@RequestMapping("/admin/apiFilter")
public class ApiFilterController {

    @Autowired
    protected CommandBus commandBus;

    @PostMapping("/add")
    @ApiOperation(value = "增加api和filter的绑定")
    public ResultData<Void> add(@RequestBody AddApiFilterCmd addApiFilterCmd) {
        return commandBus.send(addApiFilterCmd);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新api和filter的绑定")
    public ResultData<Void> update(@RequestBody UpdateApiFilterCmd updateApiFilterCmd) {
        return commandBus.send(updateApiFilterCmd);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询api-filter详情")
    public ResultData<ApiFilterDetailCO> detail(@ModelAttribute ApiFilterDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/listByApiId")
    @ApiOperation(value = "根据apiId查询api-filter详情")
    public ResultData<Map<String,Object>> listByApiId(@ModelAttribute ListApiFilterCmd cmd) {
        return commandBus.send(cmd);
    }


}
