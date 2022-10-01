package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddClusterConfigCmd;
import org.xujin.janus.app.command.cmo.DeleteClusterConfigCmd;
import org.xujin.janus.app.command.cmo.UpdateClusterConfigCmd;
import org.xujin.janus.app.command.cmo.query.PageClusterConfigCmd;
import org.xujin.janus.app.command.cmo.query.QueryClusterConfigDetailCmd;
import org.xujin.janus.app.command.co.ClusterConfigDetailCO;
import org.xujin.janus.infrastructure.utils.SessionUtils;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:59
 * @desc
 */
@RestController
@RequestMapping("/admin/clusterConfig")
@Api(tags = "clusterConfig-controller")
public class ClusterConfigController {
    @Resource
    private CommandBus commandBus;

    @PostMapping("/add")
    @ApiOperation(value = "增加集群配置")
    public ResultData<Void> add(@RequestBody AddClusterConfigCmd addClusterConfigCmd) {
        return commandBus.send(addClusterConfigCmd);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新集群配置")
    public ResultData update(@RequestBody UpdateClusterConfigCmd updateClusterConfigCmd) {
        updateClusterConfigCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(updateClusterConfigCmd);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除集群配置")
    public ResultData delete(@RequestBody DeleteClusterConfigCmd deleteClusterConfigCmd) {
        deleteClusterConfigCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(deleteClusterConfigCmd);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询集群配置详情")
    public ResultData<ClusterConfigDetailCO> detail(@ModelAttribute QueryClusterConfigDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/page")
    @ApiOperation(value = "查询集群配置分页")
    public ResultData<PageResult<ClusterConfigDetailCO>> page(@ModelAttribute PageClusterConfigCmd pageClusterConfigCmd) {
        return commandBus.send(pageClusterConfigCmd);
    }

}
