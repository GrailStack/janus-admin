package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.ClusterConfigE;
import org.xujin.janus.domain.user.ports.ClusterConfigPort;
import org.xujin.janus.infrastructure.converter.ClusterConfigConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterConfigMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/5/27 10:07
 * @desc
 */
@Adapter
public class ClusterConfigAdapter implements ClusterConfigPort {
    @Autowired
    private ClusterConfigConverter clusterConfigConverter;

    @Autowired
    private ClusterConfigMapper clusterConfigMapper;

    @Override
    public void save(ClusterConfigE clusterConfigE) {
        ClusterConfigDO clusterConfigDO = clusterConfigConverter.entityToData(clusterConfigE);
        clusterConfigMapper.insert(clusterConfigDO);
    }

    @Override
    public void update(ClusterConfigE clusterConfigE) {
        ClusterConfigDO clusterConfigDO = clusterConfigConverter.entityToData(clusterConfigE);
        clusterConfigMapper.updateById(clusterConfigDO);
    }

    @Override
    public void delete(ClusterConfigE clusterConfigE) {
        ClusterConfigDO clusterConfigDO = clusterConfigConverter.entityToData(clusterConfigE);
        clusterConfigDO.setIsDeleted(new Byte("1"));
        clusterConfigMapper.updateById(clusterConfigDO);
    }
}
