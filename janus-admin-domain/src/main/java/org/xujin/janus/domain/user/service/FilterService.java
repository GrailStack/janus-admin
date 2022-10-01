package org.xujin.janus.domain.user.service;

import org.xujin.janus.domain.user.entity.ClusterFilterE;
import org.xujin.janus.domain.user.entity.FilterE;

import java.math.BigInteger;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:41
 * @desc
 */
public interface FilterService {

    /**
     * 新增filter
     * @param filterE
     */
    void addFilter(FilterE filterE);

    /**
     * 删除filter
     * @param filterE
     */
    void deleteFilter(FilterE filterE);

    /**
     * 更新filter
     * @param filterE
     */
    void updateFilter(FilterE filterE);

    /**
     * 查询filter
     * @param id
     * @return
     */
    FilterE findById(BigInteger id);

    /**
     * 根据集群编码查询全局filter
     * @param clusterCode
     * @return
     */
    List<String> globalFilters(String clusterCode);

    /**
     * 新增集群与filter的绑定
     * @param clusterFilterE
     */
    void addClusterFilter(List<ClusterFilterE> clusterFilterE);

    /**
     * 根据clusterCode删除
     * @param clusterCode
     */
    void deleteClusterFilter(String clusterCode);
}
