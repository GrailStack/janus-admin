package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.ports.ClusterPort;
import org.xujin.janus.infrastructure.converter.ClusterConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/14 18:47
 * @desc
 */
@Adapter
public class ClusterAdapter implements ClusterPort {

    @Resource
    ClusterMapper clusterMapper;
    @Resource
    ClusterConverter converter;

    @Override
    public void addCluster(ClusterEntity clusterEntity) {
        ClusterDO clusterDO = converter.entityToData(clusterEntity);
        clusterMapper.insert(clusterDO);
    }

    @Override
    public void deleteCluster(ClusterEntity clusterEntity) {
        ClusterDO clusterDO = converter.entityToData(clusterEntity);
        clusterMapper.updateById(clusterDO);
    }

    @Override
    public void updateCluster(ClusterEntity clusterEntity) {
        ClusterDO clusterDO = converter.entityToData(clusterEntity);
        clusterMapper.updateById(clusterDO);
    }

    @Override
    public Map<String, String> findClusterNames(List<String> clusterCodes) {
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("code", clusterCodes);
        return clusterMapper
                .selectList(queryWrapper)
                .stream()
                .collect(Collectors.toMap(ClusterDO::getCode, ClusterDO::getName, (name1, name2) -> name1));
    }

    @Override
    public String findClusterName(String clusterCode) {
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", clusterCode);
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 1");
        ClusterDO clusterDO = clusterMapper.selectOne(queryWrapper);
        return clusterDO == null ? null : clusterDO.getName();
    }

}
