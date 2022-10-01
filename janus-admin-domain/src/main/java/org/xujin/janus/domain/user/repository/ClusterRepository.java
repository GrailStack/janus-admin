package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.ports.ClusterPort;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/14 18:42
 * @desc
 */
@DomainRepository
public class ClusterRepository {

    @Resource
    private ClusterPort clusterPort;

    public void save(ClusterEntity clusterEntity) {
        clusterPort.addCluster(clusterEntity);
    }

    public void delete(ClusterEntity clusterEntity) {
        clusterPort.deleteCluster(clusterEntity);
    }

    public void updateCluster(ClusterEntity clusterEntity) {
        clusterPort.updateCluster(clusterEntity);
    }

}
