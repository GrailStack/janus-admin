package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.UserRolesE;
import org.xujin.janus.domain.user.valueobject.UserRolesVO;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/15 17:22
 * @desc
 */
@Port
public interface UserRolePort {

    Long createUserRole(String userName, String role);

    void save(UserRolesVO userRolesVO);

    void update(UserRolesVO userRolesVO);

    List<UserRolesE> queryByRole(String role);

    /**
     * 根据userName判断是否为超管
     * @param userName
     * @return
     */
    boolean isAdmin(String userName);

}
