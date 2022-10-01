package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.valueobject.RolePermissionVO;

/**
 * @author chenglong.ren
 * @date 2020/4/16 11:26
 * @desc
 */
@Port
public interface RolePermissionPort {
    void save(RolePermissionVO rolePermissionVO);

    void deleteByKeyAndPid(RolePermissionVO rolePermissionVO);

    void deleteByKey(String key);

    void update(RolePermissionVO rolePermissionVO);



}
