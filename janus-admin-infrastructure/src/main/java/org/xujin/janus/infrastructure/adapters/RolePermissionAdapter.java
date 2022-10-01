package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.ports.RolePermissionPort;
import org.xujin.janus.domain.user.valueobject.RolePermissionVO;
import org.xujin.janus.infrastructure.converter.RolePermissionConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.RolePermissionMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RolePermissionDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/4/16 11:27
 * @desc
 */
@Adapter
public class RolePermissionAdapter implements RolePermissionPort {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RolePermissionConverter rolePermissionConverter;

    @Override
    public void save(RolePermissionVO rolePermissionVO) {
        RolePermissionDO rolePermissionDO = rolePermissionConverter.entityToData(rolePermissionVO);
        rolePermissionMapper.insert(rolePermissionDO);
    }

    @Override
    public void deleteByKeyAndPid(RolePermissionVO rolePermissionVO) {
        rolePermissionMapper.deleteByPidAndRole(rolePermissionVO.getPermissionId(),rolePermissionVO.getRole());
    }

    @Override
    public void deleteByKey(String key) {
        rolePermissionMapper.deleteByRole(key);
    }

    @Override
    public void update(RolePermissionVO rolePermissionVO) {
        RolePermissionDO rolePermissionDO = rolePermissionConverter.entityToData(rolePermissionVO);
        rolePermissionMapper.deleteByRole(rolePermissionVO.getRole());
        rolePermissionMapper.insert(rolePermissionDO);
    }
}
