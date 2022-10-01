package org.xujin.janus.app.command.co;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserCO {

    private Long id;

    private String userName;

    private String name;

    private String role;

    private String email;

    private String password;

    private byte status;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Byte isDeleted=0;
}
