package org.xujin.janus.app.event;

import org.xujin.halo.annotation.event.EventHandler;
import org.xujin.halo.dto.Response;
import org.xujin.halo.event.EventHandlerI;
import org.xujin.janus.domain.user.entity.UserE;
import org.xujin.janus.domain.user.event.CreateUserEvent;
import org.xujin.janus.domain.user.service.UserService;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.common.MD5;
import org.xujin.janus.infrastructure.tunnel.db.dao.UserMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yage.luan
 * created at 2019/5/11 19:10
 **/
@EventHandler
public class CreateUserEventHandler implements EventHandlerI<Response, CreateUserEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Response execute(CreateUserEvent event) {
        if (StringUtils.isEmpty(event.getRole()) ||StringUtils.isEmpty(event.getUserName())) {
            return Response.buildSuccess();
        }
        UserDO userDto=userMapper.findUserByUserName(event.getUserName());
        if(null!=userDto){
            return Response.buildSuccess();
        }
        UserE userE=new UserE();
        userE.setPassword(MD5.md5(event.getPassword()));
        userE.setName(event.getUserName());
        userE.setStatus((byte) HaloConstant.ENABLE);
        userE.setUserName(event.getUserName());
        userE.setEmail(event.getUserName()+"@xujin.org");
        userService.saveUser(userE);
        return Response.buildSuccess();
    }

}
