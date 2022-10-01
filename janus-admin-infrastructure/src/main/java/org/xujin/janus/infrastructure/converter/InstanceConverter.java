package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.InstanceE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;
import org.springframework.stereotype.Component;

@Component
public class InstanceConverter implements ConverterI {

    public InstanceDO entityToData(InstanceE instanceE) {
        if (null == instanceE) {
            return null;
        }
        InstanceDO instanceDO = BeanMapper.map(instanceE, InstanceDO.class);
        return instanceDO;
    }


}
