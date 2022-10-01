package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.entity.ClusterFilterE;
import org.xujin.janus.domain.user.ports.ClusterFilterPort;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:49
 * @desc
 */
@DomainRepository
public class ClusterFilterRepository {

    @Resource
    private ClusterFilterPort clusterFilterPort;

    public void save(ClusterFilterE clusterFilterE) {
//        clusterFilterPort.save(clusterFilterE);
    }
}
