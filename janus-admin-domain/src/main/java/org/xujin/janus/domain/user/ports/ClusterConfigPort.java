package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.ClusterConfigE;

/**
 * @author chenglong.ren
 * @date 2020/5/27 9:56
 * @desc
 */
@Port
public interface ClusterConfigPort {

     void save(ClusterConfigE clusterConfigE);

    void update(ClusterConfigE clusterConfigE);

    void delete(ClusterConfigE clusterConfigE);
}
