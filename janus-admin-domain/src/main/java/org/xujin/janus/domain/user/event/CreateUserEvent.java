package org.xujin.janus.domain.user.event;

import org.xujin.halo.event.Event;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yage.luan
 * created at 2019/5/11 19:07
 **/
@Data
@Accessors(chain = true)
public class CreateUserEvent extends Event {

    private String userName;

    private String role;

    private String password;

}
