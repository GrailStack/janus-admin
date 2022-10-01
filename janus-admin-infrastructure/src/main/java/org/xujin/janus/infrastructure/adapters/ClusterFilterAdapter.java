package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.ClusterFilterE;
import org.xujin.janus.domain.user.ports.ClusterFilterPort;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.converter.ClusterFilterConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterFilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterFilterDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:51
 * @desc
 */
@Adapter
public class ClusterFilterAdapter implements ClusterFilterPort {

    @Resource
    private ClusterFilterMapper clusterFilterMapper;

    @Resource
    private ClusterFilterConverter clusterFilterConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<ClusterFilterE> clusterFilterEs) {
        List<ClusterFilterDO> clusterFilterDO = clusterFilterConverter.entityToDatas(clusterFilterEs);
        if (CollectionUtils.isNotEmpty(clusterFilterDO)) {
            ClusterFilterDO clusterFilterDO1 = clusterFilterDO.get(0);
            if (null != clusterFilterDO1 && !StringUtils.isEmpty(clusterFilterDO1.getClusterCode())) {
                QueryWrapper<ClusterFilterDO> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("cluster_code", clusterFilterDO1.getClusterCode());
                queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
                List<ClusterFilterDO> clusterFilterDOS = clusterFilterMapper.selectList(queryWrapper);
                if (CollectionUtils.isNotEmpty(clusterFilterDOS)) {
                    clusterFilterDOS.forEach(x->{
                        x.setIsDeleted(HaloConstant.IS_DELETED_TRUE);
                        clusterFilterMapper.updateById(x);
                    });
                }
            }
            clusterFilterDO.forEach(x->{
                if (x.getFilterId() != null) {
                    clusterFilterMapper.insert(x);
                }
            });
        }
    }

    @Override
    public void delete(ClusterFilterE clusterFilterE) {
        ClusterFilterDO clusterFilterDO = clusterFilterConverter.entityToData(clusterFilterE);
        QueryWrapper<ClusterFilterDO> wrapper = new QueryWrapper<>();
        wrapper.eq("filter_id", clusterFilterDO.getFilterId());
        wrapper.eq("is_delete", clusterFilterDO.getIsDeleted());
        clusterFilterMapper.update(clusterFilterDO, wrapper);
    }

    @Override
    public void deleteAll(String clusterCode) {
        QueryWrapper<ClusterFilterDO> wrapper = new QueryWrapper<>();
        wrapper.eq("cluster_code", clusterCode);
        clusterFilterMapper.delete(wrapper);
    }

    @Override
    public void update(ClusterFilterE clusterFilterE) {
        ClusterFilterDO clusterFilterDO = clusterFilterConverter.entityToData(clusterFilterE);
        QueryWrapper<ClusterFilterDO> wrapper = new QueryWrapper<>();
        wrapper.eq("filter_id", clusterFilterDO.getFilterId());
        clusterFilterMapper.update(clusterFilterDO, wrapper);
    }

    @Override
    public List<Integer> listFilterIdByClusterCodes(List<String> clusterCodes) {
        if (!CollectionUtils.isNotEmpty(clusterCodes)) {
            return new ArrayList<>();
        }
        QueryWrapper<ClusterFilterDO> wrapper = new QueryWrapper<>();
        wrapper.in("cluster_code", clusterCodes);
        wrapper.eq("is_deleted", 0);
        List<ClusterFilterDO> clusterFilterDOS = clusterFilterMapper.selectList(wrapper);
        return Optional.ofNullable(clusterFilterDOS).orElse(Lists.newArrayList())
                .stream()
                .map(ClusterFilterDO::getFilterId)
                .collect(Collectors.toList());
    }
}
