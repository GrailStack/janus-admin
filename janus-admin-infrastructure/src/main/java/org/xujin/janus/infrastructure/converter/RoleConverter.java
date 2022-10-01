package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.RoleE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements ConverterI {

    public RoleDO entityToData(RoleE roleE) {
        RoleDO roleDO = BeanMapper.map(roleE, RoleDO.class);
        return roleDO;
    }

    public RoleE dataToEntity(RoleDO roleDO) {
        RoleE roleE = BeanMapper.map(roleDO, RoleE.class);
        return roleE;
    }

}
