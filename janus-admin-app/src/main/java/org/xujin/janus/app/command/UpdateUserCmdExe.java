package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.UpdateUserCmd;
import org.xujin.janus.domain.user.entity.UserE;
import org.xujin.janus.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 修改或跟新用户的命令
 */
@CmdHandler
public class UpdateUserCmdExe implements CommandExecutorI<ResultData<Void>, UpdateUserCmd> {

    @Autowired
    private UserService userService;

    @Override
    public ResultData<Void> execute(UpdateUserCmd cmd) {
        UserE userE= BeanMapper.map(cmd,UserE.class);
        userE.setUserName(cmd.getUserName());
        userService.saveOrUpdateUser(userE,cmd.getRole());
        return ResultData.success(null);
    }
}