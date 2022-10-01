package org.xujin.janus.domain.user.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author chenglong.ren
 * @date 2020/4/16 11:35
 * @desc
 */
@Data
public class UserRolesE {
    private String username;

    private String role;

    private Long id;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Byte isDeleted=0;
}
