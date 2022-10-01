package org.xujin.janus.app.command.cmo;

import lombok.Data;

/**
 * 更新用户
 * @author xujin
 */
@Data
public class UpdateUserCmd extends CommonCommand {

    private Long id;

    private String name;

    private String userName;

    private String email;

    private String role;

    private byte status;

    private String password;


}
