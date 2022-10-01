package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.QueryJvmMemoryHeapInfoCmd;
import org.xujin.janus.app.command.cmo.query.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author chenglong.ren
 * @date 2020/6/10 17:51
 * @desc
 */
@RestController
@RequestMapping("/admin/influxDB")
@Api(tags = "influxDB-controller")
public class InfluxDBController {

    @Resource
    private CommandBus commandBus;

    @GetMapping("/searchQPS")
    @ApiOperation(value = "查询QPS")
    public ResultData<Map<String, Object>> searchQPS(@ModelAttribute QueryQpsCmd queryQpsCmd) {
        return commandBus.send(queryQpsCmd);
    }

    @GetMapping("/searchRequestError")
    @ApiOperation(value = "查询RequestError")
    public ResultData<Map<String, Object>> searchRequestError(@ModelAttribute QueryRequestErrorCmdCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/searchRouteCount")
    @ApiOperation(value = "查询RouteCount")
    public ResultData<Map<String, Object>> searchRouteCount(@ModelAttribute QueryRouteCountCmdCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/getAllNums")
    @ApiOperation(value = "统计集群,API,告警,实例总数量")
    public ResultData<Map<String, Integer>> getAllNums() {
        QueryAllCountCmd cmd = new QueryAllCountCmd();
        return commandBus.send(cmd);
    }

    @GetMapping("/searchDiskFree")
    @ApiOperation(value = "查询DiskInfo")
    public ResultData<Map<String, Object>> searchDiskInfo(@ModelAttribute QueryDiskFreeCmdCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/searchJvmMemory")
    @ApiOperation(value = "查询JvmMemory")
    public ResultData<Map<String, Object>> searchJvmMemory(@ModelAttribute QueryJvmMemoryUsedCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/searchJvmHeapInfo")
    @ApiOperation(value = "查询JvmHeapInfo")
    public ResultData<Map<String, Object>> searchJvmHeapInfo(@ModelAttribute QueryJvmMemoryHeapInfoCmd cmd) {
        return commandBus.send(cmd);
    }
}
