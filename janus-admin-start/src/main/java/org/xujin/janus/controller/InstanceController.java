package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.InstanceDetailCmd;
import org.xujin.janus.app.command.cmo.query.PageInstanceCmd;
import org.xujin.janus.app.command.cmo.query.QueryInstanceCountCmd;
import org.xujin.janus.app.command.co.InstanceDetailCO;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:40
 * @desc
 */
@RestController
@RequestMapping("/admin/instance")
@Api(tags = "instance-controller")
public class InstanceController {

    @Resource
    private CommandBus commandBus;

    @GetMapping("/detail")
    @ApiOperation(value = "查询实例详情")
    public ResultData<InstanceDetailCO> detail(@ModelAttribute InstanceDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/page")
    @ApiOperation(value = "查询告警分页")
    public ResultData<PageResult<InstanceDetailCO>> page(@ModelAttribute PageInstanceCmd pageInstanceCmd) {
        return commandBus.send(pageInstanceCmd);
    }

    @GetMapping("/countInstance")
    @ApiOperation(value = "查询实例数量")
    public ResultData<Integer> countInstance() {
        return commandBus.send(new QueryInstanceCountCmd());
    }

}
