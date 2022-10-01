package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.RoleE;
import org.xujin.janus.domain.user.ports.RolePort;
import org.xujin.janus.domain.user.repository.RolePermissionRepository;
import org.xujin.janus.domain.user.repository.RoleRepository;
import org.xujin.janus.domain.user.valueobject.RolePermissionVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jin.xu
 * @date 2019/5/14
 */
@Factory
public class RoleFactory {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Autowired
    RolePort rolePort;

    public RoleE create() {
        RoleE roleE = new RoleE();
        roleE.setRoleRepository(roleRepository);
        return roleE;
    }

    public RolePermissionVO createRolePerVO(String role, Long pid){
        RolePermissionVO rolePermissionVO=new RolePermissionVO();
        rolePermissionVO.setPermissionId(pid);
        rolePermissionVO.setRole(role);
        rolePermissionVO.setRolePRepository(rolePermissionRepository);
        return rolePermissionVO;
    }

    public RoleE getRole(Long id){
        return BeanMapper.map(rolePort.getRoleById(id),RoleE.class);
    }


}
