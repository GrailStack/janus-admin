package org.xujin.janus.infrastructure.security.user;

import lombok.Getter;

import java.util.Arrays;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/4 10:42
 **/
public enum UserRoleEnum {

    //admin角色
    ADMIN("ADMIN", "管理员用户"),
    //普通用户角色
    USER("USER", "普通用户"),
    //黑名单用户
    BLACK("BLACK", "黑名单用户");


    UserRoleEnum(String role, String desc) {
        this.role = role;
        this.desc = desc;
    }

    @Getter
    private String role;
    @Getter
    private String desc;

    public static UserRoleEnum parseToEnum(String role) {
        return Arrays.stream(UserRoleEnum.values())
                .filter(roleEnum -> roleEnum.getRole().equalsIgnoreCase(role))
                .findFirst()
                .orElse(null);
    }

}
