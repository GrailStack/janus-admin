package org.xujin.janus.controller;

import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.LoginUserCmd;
import org.xujin.janus.app.command.co.LogInCO;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yage.luan
 * created at 2019/5/9 18:38
 **/
@RestController
@RequestMapping("/admin")
@Api("用户登录")
public class LoginController {

    @Autowired
    private CommandBus commandBus;

    @PostMapping("/login")
    public ResultData<LogInCO> login(@RequestBody LoginUserCmd loginUserCmd) {
        return commandBus.send(loginUserCmd);
    }

    @GetMapping("/logout")
    public ResultData<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultData.success("redirect:/login");
    }

}
