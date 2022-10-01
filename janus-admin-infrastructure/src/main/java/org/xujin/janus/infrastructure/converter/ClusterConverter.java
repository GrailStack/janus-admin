package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/4/15 10:16
 * @desc
 */
@Component
public class ClusterConverter implements ConverterI {

    public ClusterDO entityToData(ClusterEntity clusterEntity) {
        if (null == clusterEntity) {
            return null;
        }
        ClusterDO clusterDO = BeanMapper.map(clusterEntity, ClusterDO.class);
        return clusterDO;
    }

    public ClusterDetailCo dataToEntity(ClusterDO clusterDO) {
        if (null == clusterDO) {
            return null;
        }
        ClusterDetailCo clusterDetailCo = BeanMapper.map(clusterDO, ClusterDetailCo.class);
        return clusterDetailCo;
    }
}
