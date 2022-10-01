package org.xujin.janus.domain.user.ports;

import com.google.common.collect.Lists;
import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.ClusterUserE;

import java.math.BigInteger;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:21
 * @desc
 */
@Port
public interface ClusterUserPort {


    /**
     * 保存集群与用户的关系
     * @param clusterUserE
     */
    void save(ClusterUserE clusterUserE);

    /**
     * 更新集群与用户的关系
     * @param clusterUserE
     */
    void update(ClusterUserE clusterUserE);

    /**
     * 删除集群与用户的关系
     * @param clusterUserE
     */
    void delete(ClusterUserE clusterUserE);

    /**
     * 查询集群与用户的关系
     * @param id
     * @return
     */
    ClusterUserE findById(BigInteger id);

    /**
     * 查询用户可见的集群
     * @param userName
     * @return
     */
    List<String> findClusterCodeByUserName(String userName);

}
