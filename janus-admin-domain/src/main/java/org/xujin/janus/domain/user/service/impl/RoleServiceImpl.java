package org.xujin.janus.domain.user.service.impl;

import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.user.entity.RoleE;
import org.xujin.janus.domain.user.entity.UserRolesE;
import org.xujin.janus.domain.user.factory.RoleFactory;
import org.xujin.janus.domain.user.ports.RolePermissionPort;
import org.xujin.janus.domain.user.ports.RolePort;
import org.xujin.janus.domain.user.ports.UserRolePort;
import org.xujin.janus.domain.user.repository.RolePermissionRepository;
import org.xujin.janus.domain.user.repository.RoleRepository;
import org.xujin.janus.domain.user.repository.UserRolesRepository;
import org.xujin.janus.domain.user.service.RoleService;
import org.xujin.janus.domain.user.valueobject.RolePermissionVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色相关域服务
 * @author xujin
 */
@DomainService
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePort rolePort;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleFactory roleFactory;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Resource
    private RolePermissionPort rolePermissionPort;

    @Resource
    private UserRolePort userRolePort;


    @Override
    public void saveOrUpdateRole(RoleE roleE, List<Long> pIdList) {
        roleE.setRoleRepository(roleRepository);
        rolePort.save(roleE);
        for (Long id:pIdList) {
            RolePermissionVO rolePermissionVO=roleFactory.createRolePerVO(roleE.getKey(),id);
            rolePermissionPort.deleteByKeyAndPid(rolePermissionVO);
            rolePermissionPort.save(rolePermissionVO);
        }

    }

    @Override
    public void deleteRole(Long id){
        RoleE roleE=roleFactory.getRole(id);
        List<UserRolesE> userRolesDOList=userRolePort.queryByRole(roleE.getKey());
        if(null!=userRolesDOList&&userRolesDOList.size()>0){
            throw new BusinessException("400","删除的角色目前正在使用,不允许删除");
        }
        roleE.setRoleRepository(roleRepository);
        rolePermissionPort.deleteByKey(roleE.getKey());
        rolePort.delete(roleE);
    }

}
