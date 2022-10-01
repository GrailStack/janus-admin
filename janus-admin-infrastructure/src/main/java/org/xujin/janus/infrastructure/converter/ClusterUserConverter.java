package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.ClusterUserE;
import org.xujin.janus.infrastructure.ClusterUserDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterUserDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:36
 * @desc
 */
@Component
public class ClusterUserConverter implements ConverterI {

    public ClusterUserDO entityToData(ClusterUserE clusterUserE) {
        return BeanMapper.map(clusterUserE, ClusterUserDO.class);
    }

    public ClusterUserE dataToEntity(ClusterUserDO clusterUserDO) {
        return BeanMapper.map(clusterUserDO, ClusterUserE.class);
    }

    public ClusterUserDetailCo entityToCo(ClusterUserE clusterUserE) {
        return BeanMapper.map(clusterUserE, ClusterUserDetailCo.class);
    }
}
