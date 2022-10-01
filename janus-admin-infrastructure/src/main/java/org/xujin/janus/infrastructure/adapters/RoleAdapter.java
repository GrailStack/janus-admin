package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.user.entity.RoleE;
import org.xujin.janus.domain.user.ports.RolePort;
import org.xujin.janus.infrastructure.converter.RoleConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.RoleMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/4/16 11:17
 * @desc
 */
@Adapter
public class RoleAdapter implements RolePort {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleConverter roleConverter;

    @Override
    public void save(RoleE roleE) {
        RoleDO roleDO = roleConverter.entityToData(roleE);
        if (null==roleDO.getId()) {
            RoleDO oldRoleDO=roleMapper.queryRoleByKey(roleDO.getKey());
            if(null!=oldRoleDO){
                throw new BusinessException("400","角色的key不允许重复");
            }
            roleMapper.insert(roleDO);
        } else {
            roleMapper.updateById(roleDO);
        }
    }

    @Override
    public void delete(RoleE roleE) {
        RoleDO roleDO = roleConverter.entityToData(roleE);
        roleMapper.deleteById(roleDO);
    }

    @Override
    public RoleE getRoleById(Long id) {
        RoleDO roleDO = roleMapper.selectById(id);
        return roleConverter.dataToEntity(roleDO);
    }
}
