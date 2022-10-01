package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.ClusterUserE;
import org.xujin.janus.domain.user.repository.ClusterUserRepository;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:20
 * @desc
 */
@Factory
public class ClusterUserFactory {

    @Resource
    private ClusterUserRepository clusterUserRepository;

    public ClusterUserE createClusterUserE() {
        ClusterUserE clusterUserE = new ClusterUserE();
        clusterUserE.setClusterUserRepository(clusterUserRepository);
        return clusterUserE;
    }
}
