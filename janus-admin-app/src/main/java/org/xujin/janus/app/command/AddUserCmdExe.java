package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.AddUserCmd;
import org.xujin.janus.domain.user.entity.UserE;
import org.xujin.janus.domain.user.service.UserService;
import org.xujin.janus.infrastructure.common.MD5;
import org.xujin.janus.infrastructure.tunnel.db.dao.UserMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改或跟新用户的命令
 */
@CmdHandler
public class AddUserCmdExe implements CommandExecutorI<ResultData<Void>, AddUserCmd> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultData<Void> execute(AddUserCmd cmd) {
        UserDO userDto=userMapper.findUserByUserName(cmd.getUserName());
        if(null!=userDto){
            throw new BusinessException("400","用户名:"+cmd.getUserName()+"不能重复");
        }
        UserE userE= BeanMapper.map(cmd,UserE.class);
        userE.setPassword(MD5.md5(cmd.getPassword()));
        userService.saveOrUpdateUser(userE,cmd.getRole());
        return ResultData.success(null);
    }
}