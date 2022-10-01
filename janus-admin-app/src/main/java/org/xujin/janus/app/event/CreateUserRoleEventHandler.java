package org.xujin.janus.app.event;

import org.xujin.halo.annotation.event.EventHandler;
import org.xujin.halo.dto.Response;
import org.xujin.halo.event.EventHandlerI;
import org.xujin.janus.domain.user.event.CreateUserRoleEvent;
import org.xujin.janus.domain.user.ports.UserRolePort;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yage.luan
 * created at 2019/5/11 19:10
 **/
@EventHandler
public class CreateUserRoleEventHandler implements EventHandlerI<Response, CreateUserRoleEvent> {

    @Autowired
    private UserRolePort userRolePort;

    @Override
    public Response execute(CreateUserRoleEvent event) {
        if (StringUtils.isNotEmpty(event.getRole()) && StringUtils.isNotEmpty(event.getUserName())) {
            userRolePort.createUserRole(event.getUserName(), event.getRole());
        }
        return Response.buildSuccess();
    }

}
