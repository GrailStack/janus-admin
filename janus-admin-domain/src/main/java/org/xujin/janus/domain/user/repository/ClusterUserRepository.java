package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.entity.ClusterUserE;
import org.xujin.janus.domain.user.ports.ClusterUserPort;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:21
 * @desc
 */
@DomainRepository
public class ClusterUserRepository {
    @Resource
    private ClusterUserPort clusterUserPort;

    public void save(ClusterUserE clusterUserE) {
        clusterUserPort.save(clusterUserE);
    }

    public void update(ClusterUserE clusterUserE) {
        clusterUserPort.update(clusterUserE);
    }

    public void delete(ClusterUserE clusterUserE) {
        clusterUserPort.delete(clusterUserE);
    }
}
