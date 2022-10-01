package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.ClusterEntity;

import java.util.List;
import java.util.Map;

/**
 * @author chenglong.ren
 * @date 2020/4/14 18:43
 * @desc
 */
@Port
public interface ClusterPort {
    /**
     * 新增集群
     *
     * @param clusterEntity
     */
    void addCluster(ClusterEntity clusterEntity);

    /**
     * 删除集群
     *
     * @param clusterEntity
     */
    void deleteCluster(ClusterEntity clusterEntity);

    /**
     * 更新集群
     *
     * @param clusterEntity
     */
    void updateCluster(ClusterEntity clusterEntity);

    /**
     * @param clusterCodes
     * @return code to name map
     */
    Map<String, String> findClusterNames(List<String> clusterCodes);

    String findClusterName(String clusterCode);

}
