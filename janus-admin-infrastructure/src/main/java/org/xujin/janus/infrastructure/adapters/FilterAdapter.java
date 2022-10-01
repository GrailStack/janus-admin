package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.ports.FilterPort;
import org.xujin.janus.infrastructure.converter.FilterConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:32
 * @desc
 */
@Adapter
public class FilterAdapter implements FilterPort {

    @Resource
    private FilterConverter filterConverter;

    @Resource
    private FilterMapper filterMapper;

    @Override
    public void addFilter(FilterE filterE) {
        FilterDO filterDO = filterConverter.entityToData(filterE);
        filterMapper.insert(filterDO);
        filterE.setId(filterDO.getId());
    }

    @Override
    public void deleteFilter(FilterE filterE) {
        FilterDO filterDO = filterConverter.entityToData(filterE);
        filterMapper.updateById(filterDO);
    }

    @Override
    public void updateFilter(FilterE filterE) {
        FilterDO filterDO = filterConverter.entityToData(filterE);
        filterMapper.updateById(filterDO);
    }

    @Override
    public FilterE findById(BigInteger id) {
        return filterConverter.dataToEntity(filterMapper.selectById(id));
    }

    @Override
    public List<String> findGlobalNames(List<Integer> ids) {
        QueryWrapper<FilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.eq("is_global", 1);
        List<FilterDO> filterDOS = filterMapper.selectList(queryWrapper);
        return Optional.ofNullable(filterDOS).orElse(Arrays.asList())
                .stream()
                .map(FilterDO::getName)
                .collect(Collectors.toList())
                ;
    }
}
