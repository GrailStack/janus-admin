package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.ClusterConfigE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/5/27 10:08
 * @desc
 */
@Component
public class ClusterConfigConverter implements ConverterI {

    public ClusterConfigDO entityToData(ClusterConfigE clusterConfigE) {
        return BeanMapper.map(clusterConfigE, ClusterConfigDO.class);
    }

    public ClusterConfigE dataToEntity(ClusterConfigDO clusterConfigDO) {
        return BeanMapper.map(clusterConfigDO, ClusterConfigE.class);
    }

}
