package org.xujin.janus.controller;

import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.ChangeOnlineStateCmd;
import org.xujin.janus.app.command.cmo.UpdateAlarmCmd;
import org.xujin.janus.client.cmo.ChangeOnlineCmd;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/6/12 14:20
 * @desc
 */
@RestController
@RequestMapping("/admin/server")
@Api("janusServer管理")
public class ServerController {
    @Resource
    private CommandBus commandBus;

    @PostMapping("/changeOnline")
    @ApiOperation(value = "更新服务状态")
    public ResultData changeOnline(@RequestBody ChangeOnlineStateCmd changeOnlineStateCmd) {
        return commandBus.send(changeOnlineStateCmd);
    }
}
