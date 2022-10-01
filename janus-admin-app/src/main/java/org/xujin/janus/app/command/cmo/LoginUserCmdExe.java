package org.xujin.janus.app.command.cmo;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.event.EventBus;
import org.xujin.halo.shiro.jwt.JwtUtil;
import org.xujin.janus.app.command.co.LogInCO;
import org.xujin.janus.domain.user.event.CreateUserEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

@CmdHandler
@Slf4j
public class LoginUserCmdExe implements CommandExecutorI<ResultData<LogInCO>, LoginUserCmd> {

    @Autowired
    private EventBus eventBus;

    @Override
    public ResultData<LogInCO> execute(LoginUserCmd loginUserCmd) {
        ResultData<LogInCO> resultData = new ResultData<>();
        if (null==loginUserCmd) {
            resultData.setMsgCode("401");
            resultData.setMsgContent("login failed");
            return resultData;
        }
        String username=loginUserCmd.getUsername();
        String password=loginUserCmd.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            resultData.setMsgCode("401");
            resultData.setMsgContent("login failed");
            return resultData;
        }
        username = username.trim();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            LogInCO logInCO = new LogInCO().setUsername(username).setToken(JwtUtil.createToken(username));
            resultData.setData(logInCO);
            try {
                createUserByEvent(username,password);
            } catch (Exception e) {
               log.info("create user by event fail:{}",e);
            }
        } catch (Exception ex) {
            resultData.setMsgCode("401");
            resultData.setMsgContent("login failed");
            return resultData;
        }
        return resultData;
    }

    private void createUserByEvent(String username,String password) {
        CreateUserEvent createUserEvent=new CreateUserEvent();
        createUserEvent.setUserName(username);
        createUserEvent.setPassword(password);
        createUserEvent.setRole("USER");
        eventBus.asyncPublishEvent(createUserEvent);
    }
}
