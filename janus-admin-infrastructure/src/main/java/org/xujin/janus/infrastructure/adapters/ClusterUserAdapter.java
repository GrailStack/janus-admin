package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.ClusterUserE;
import org.xujin.janus.domain.user.ports.ClusterUserPort;
import org.xujin.janus.infrastructure.converter.ClusterUserConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterUserMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterUserDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:33
 * @desc
 */
@Adapter
public class ClusterUserAdapter implements ClusterUserPort {

    @Resource
    private ClusterUserMapper mapper;

    @Resource
    private ClusterUserConverter converter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ClusterUserE clusterUserE) {
        if (CollectionUtils.isNotEmpty(clusterUserE.getAddList())) {
            QueryWrapper<ClusterUserDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cluster_code", clusterUserE.getClusterCode());
            queryWrapper.eq("is_deleted", "0");
            List<ClusterUserDO> clusterUserDOS = mapper.selectList(queryWrapper);
            if (CollectionUtils.isNotEmpty(clusterUserDOS)) {
                clusterUserDOS.forEach(x -> {
                    x.setIsDeleted(new Byte("1"));
                    mapper.updateById(x);
                });
            }
            //新增批量不为空
            clusterUserE.getAddList().forEach(x->{
                ClusterUserDO clusterUserDO = new ClusterUserDO();
                clusterUserDO.setUserName(x);
                clusterUserDO.setClusterCode(clusterUserE.getClusterCode());
                mapper.insert(clusterUserDO);
            });
        }
        if (CollectionUtils.isEmpty(clusterUserE.getAddList()) && !StringUtils.isEmpty(clusterUserE.getClusterCode())) {
            QueryWrapper<ClusterUserDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cluster_code", clusterUserE.getClusterCode());
            queryWrapper.eq("is_deleted", "0");
            List<ClusterUserDO> clusterUserDOS = mapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(clusterUserDOS)) {
                clusterUserDOS.forEach(x->{
                    x.setIsDeleted(new Byte("1"));
                    mapper.updateById(x);
                });
            }
        }
        if (!StringUtils.isEmpty(clusterUserE.getUserName())) {
            ClusterUserDO clusterUserDO = converter.entityToData(clusterUserE);
            mapper.insert(clusterUserDO);
        }
    }

    @Override
    public void update(ClusterUserE clusterUserE) {
        ClusterUserDO clusterUserDO = converter.entityToData(clusterUserE);
        mapper.updateById(clusterUserDO);
    }

    @Override
    public void delete(ClusterUserE clusterUserE) {
        ClusterUserDO clusterUserDO = converter.entityToData(clusterUserE);
        QueryWrapper<ClusterUserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", clusterUserDO.getIsDeleted());
        mapper.update(clusterUserDO, wrapper);
    }

    @Override
    public ClusterUserE findById(BigInteger id) {
        return converter.dataToEntity(mapper.selectById(id));
    }

    @Override
    public List<String> findClusterCodeByUserName(String userName) {
        QueryWrapper<ClusterUserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        wrapper.eq("user_name", userName);
        List<ClusterUserDO> clusterUserDos = mapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(clusterUserDos)) {
            List<String> collect = clusterUserDos.stream().map(ClusterUserDO::getClusterCode).collect(Collectors.toList());
            return collect;
        }
        return null;
    }
}
