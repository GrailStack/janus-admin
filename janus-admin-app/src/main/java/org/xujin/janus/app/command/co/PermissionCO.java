package org.xujin.janus.app.command.co;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PermissionCO {

    private Long id;

    private String permission;

    private String group;

    private String desc;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private boolean related;

}