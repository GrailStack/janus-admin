package org.xujin.janus.domain.user.ports;

import org.xujin.janus.domain.user.entity.ClusterFilterE;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:48
 * @desc
 */
public interface ClusterFilterPort {

    /**
     * 保存集群和filter的关系
     * @param clusterFilterEs
     */
    void saveAll(List<ClusterFilterE> clusterFilterEs);

    /**
     * 删除集群和filter的关系
     * @param clusterFilterE
     */
    void delete(ClusterFilterE clusterFilterE);

    /**
     * 删除集群下的所有filter
     * @param clusterCode
     */
    void deleteAll(String clusterCode);

    /**
     * 更新集群和filter的关系
     * @param clusterFilterE
     */
    void update(ClusterFilterE clusterFilterE);

    /**
     * 通过集群code查询关联的filter
     * @param clusterCodes
     * @return
     */
    List<Integer> listFilterIdByClusterCodes(List<String> clusterCodes);
}
