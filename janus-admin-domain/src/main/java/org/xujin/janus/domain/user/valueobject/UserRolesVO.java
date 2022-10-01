package org.xujin.janus.domain.user.valueobject;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.ValueObject;
import org.xujin.janus.domain.user.ports.UserRolePort;
import lombok.Data;

/**
 * @author chenglong.ren
 * @date 2020/4/15 17:20
 * @desc
 */
@ValueObject
@Data
public class UserRolesVO {
    private String username;

    private String role;

    private UserRolePort userRolePort;

    @DomainAbility
    public void save() {
        userRolePort.save(this);
    }

    @DomainAbility
    public void update() {
        userRolePort.update(this);
    }



}
