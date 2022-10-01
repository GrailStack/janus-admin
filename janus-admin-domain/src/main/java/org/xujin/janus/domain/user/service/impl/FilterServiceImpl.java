package org.xujin.janus.domain.user.service.impl;

import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.janus.domain.user.entity.ClusterFilterE;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.ports.ClusterFilterPort;
import org.xujin.janus.domain.user.ports.FilterPort;
import org.xujin.janus.domain.user.service.FilterService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:42
 * @desc
 */
@DomainService
public class FilterServiceImpl implements FilterService {

    @Resource
    private FilterPort filterPort;

    @Resource
    private ClusterFilterPort clusterFilterPort;

    private Byte isGlobal = 1;

    @Override
    public void addFilter(FilterE filterE) {
        filterPort.addFilter(filterE);
  /*      //判断是否是全局filter  如果不是  需要和集群进行绑定
        if (null != filterE.getIsGlobal() && isGlobal.equals(filterE.getIsGlobal())) {
            ClusterFilterE clusterFilterE = new ClusterFilterE();
            clusterFilterE.setFilterId(filterE.getId());
            clusterFilterE.setClusterCode(filterE.getClusterCode());
            clusterFilterPort.save(clusterFilterE);
        }*/
    }

    @Override
    public void deleteFilter(FilterE filterE) {
        filterPort.deleteFilter(filterE);
        if (null != filterE.getIsGlobal() && isGlobal.equals(filterE.getIsGlobal())) {
            ClusterFilterE clusterFilterE = new ClusterFilterE();
            clusterFilterE.setFilterId(filterE.getId());
            clusterFilterE.setIsDelete(isGlobal);
            clusterFilterPort.delete(clusterFilterE);
        }
    }

    @Override
    public void updateFilter(FilterE filterE) {
        filterPort.updateFilter(filterE);
    }

    @Override
    public FilterE findById(BigInteger id) {
        return filterPort.findById(id);
    }

    @Override
    public List<String> globalFilters(String clusterCode) {
        List<String> clusterCodes = new ArrayList<>();
        clusterCodes.add(clusterCode);
        List<Integer> bigIntegers = clusterFilterPort.listFilterIdByClusterCodes(clusterCodes);
        if (!CollectionUtils.isEmpty(bigIntegers)) {
            return filterPort.findGlobalNames(bigIntegers);
        }
        return new ArrayList<>();
    }

    @Override
    public void addClusterFilter(List<ClusterFilterE> clusterFilterE) {
        clusterFilterPort.saveAll(clusterFilterE);
    }

    @Override
    public void deleteClusterFilter(String clusterCode) {
        clusterFilterPort.deleteAll(clusterCode);
    }
}
