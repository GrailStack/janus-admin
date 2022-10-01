package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.UpdateAlarmCmd;
import org.xujin.janus.app.command.cmo.query.AlarmDetailCmd;
import org.xujin.janus.app.command.cmo.query.PageAlarmCmd;
import org.xujin.janus.app.command.cmo.query.QueryAlarmCountCmd;
import org.xujin.janus.app.command.co.AlarmDetailCO;
import org.xujin.janus.infrastructure.utils.SessionUtils;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:40
 * @desc
 */
@RestController
@RequestMapping("/admin/alarm")
@Api(tags = "alarm-controller")
public class AlarmController {

    @Resource
    private CommandBus commandBus;

    @PostMapping("/update")
    @ApiOperation(value = "更新告警")
    public ResultData update(@RequestBody UpdateAlarmCmd updateAlarmCmd) {
        updateAlarmCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(updateAlarmCmd);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询告警详情")
    public ResultData<AlarmDetailCO> detail(@ModelAttribute AlarmDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/page")
    @ApiOperation(value = "查询告警分页")
    public ResultData<PageResult<AlarmDetailCO>> page(@ModelAttribute PageAlarmCmd pageAlarmCmd) {
        return commandBus.send(pageAlarmCmd);
    }

    @GetMapping("/countAlarm")
    @ApiOperation(value = "告警数量")
    public ResultData<Integer> countAlarm() {
        return commandBus.send(new QueryAlarmCountCmd());
    }


}
