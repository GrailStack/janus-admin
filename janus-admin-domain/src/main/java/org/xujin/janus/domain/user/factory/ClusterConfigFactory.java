package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.ClusterConfigE;
import org.xujin.janus.domain.user.repository.ClusterConfigRepository;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/27 9:56
 * @desc
 */
@Factory
public class ClusterConfigFactory {
    @Resource
    private ClusterConfigRepository clusterConfigRepository;

    public ClusterConfigE createClusterConfigE() {
        ClusterConfigE clusterConfigE = new ClusterConfigE();
        clusterConfigE.setClusterConfigRepository(clusterConfigRepository);
        return clusterConfigE;
    }
}
