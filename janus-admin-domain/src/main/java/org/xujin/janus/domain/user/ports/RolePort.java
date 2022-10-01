package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.RoleE;

/**
 * @author chenglong.ren
 * @date 2020/4/16 11:15
 * @desc
 */
@Port
public interface RolePort {
    void save(RoleE roleE);

    void delete(RoleE roleE);

    RoleE getRoleById(Long id);


}
