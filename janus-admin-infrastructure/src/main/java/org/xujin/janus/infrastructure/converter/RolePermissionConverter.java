package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.valueobject.RolePermissionVO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RolePermissionDO;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionConverter implements ConverterI {

    public RolePermissionDO entityToData(RolePermissionVO rolePermissionVO) {
        return BeanMapper.map(rolePermissionVO, RolePermissionDO.class);

    }
}
