package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import lombok.Data;

/**
 * 登录命令
 * @author xujin
 */
@Data
public class LoginUserCmd extends Command {

    private String username;
    private String password;

}
