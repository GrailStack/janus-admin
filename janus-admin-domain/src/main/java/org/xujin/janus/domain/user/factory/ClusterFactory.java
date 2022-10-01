package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.repository.ClusterRepository;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/15 9:55
 * @desc
 */
@Factory
public class ClusterFactory {
    @Resource
    ClusterRepository clusterRepository;

    public ClusterEntity createClusterEntity() {
        ClusterEntity clusterEntity = new ClusterEntity();
        clusterEntity.setClusterRepository(clusterRepository);
        return clusterEntity;
    }
}
