package org.xujin.janus.domain.user.service;

import org.xujin.janus.domain.user.entity.ClusterEntity;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author chenglong.ren
 * @date 2020/4/14 18:40
 * @desc
 */
public interface ClusterService {
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

    /**
     * @param clusterCode
     * @return cluster name
     */
    String findClusterName(String clusterCode);

    List<String> findUserClusters(String clusterCode);

    <T> void fillClusterNameByCode(List<T> list, Function<T, String> codeGetter, BiConsumer<T, String> nameSetter);

}
