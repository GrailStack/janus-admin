package org.xujin.janus.domain.user.service.impl;

import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.ports.ClusterPort;
import org.xujin.janus.domain.user.ports.ClusterUserPort;
import org.xujin.janus.domain.user.service.ClusterService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/14 18:46
 * @desc
 */
@DomainService
public class ClusterServiceImpl implements ClusterService {

    @Resource
    private ClusterPort clusterPort;

    @Autowired
    private ClusterUserPort clusterUserPort;


    @Override
    public void addCluster(ClusterEntity clusterEntity) {
        clusterPort.addCluster(clusterEntity);
    }

    @Override
    public void deleteCluster(ClusterEntity clusterEntity) {
        clusterPort.deleteCluster(clusterEntity);
    }

    @Override
    public void updateCluster(ClusterEntity clusterEntity) {
        clusterPort.updateCluster(clusterEntity);
    }

    @Override
    public Map<String, String> findClusterNames(List<String> clusterCodes) {
        return clusterPort.findClusterNames(clusterCodes);
    }

    @Override
    public String findClusterName(String clusterCode) {
        return clusterPort.findClusterName(clusterCode);
    }

    @Override
    public List<String> findUserClusters(String userName) {
        return clusterUserPort.findClusterCodeByUserName(userName);
    }

    @Override
    public <T> void fillClusterNameByCode(List<T> list, Function<T, String> codeGetter, BiConsumer<T, String> nameSetter) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        List<String> codeList = list.stream().map(codeGetter).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(codeList)) {
            return;
        }
        Map<String, String> clusterNames = findClusterNames(codeList);
        list.forEach(ele -> {
            nameSetter.accept(ele, clusterNames.get(codeGetter.apply(ele)));
        });
    }

}
