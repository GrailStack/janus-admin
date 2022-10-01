package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.entity.ClusterConfigE;
import org.xujin.janus.domain.user.ports.ClusterConfigPort;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/5/27 9:55
 * @desc
 */
@DomainRepository
public class ClusterConfigRepository {
    @Autowired
    private ClusterConfigPort clusterConfigPort;

    public void save(ClusterConfigE clusterConfigE) {
        clusterConfigPort.save(clusterConfigE);
    }

    public void update(ClusterConfigE clusterConfigE) {
        clusterConfigPort.update(clusterConfigE);
    }

    public void delete(ClusterConfigE clusterConfigE) {
        clusterConfigPort.delete(clusterConfigE);
    }
}
