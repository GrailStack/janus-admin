package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.ClusterFilterE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterFilterDO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:52
 * @desc
 */
@Component
public class ClusterFilterConverter implements ConverterI {

    public ClusterFilterDO entityToData(ClusterFilterE clusterFilterE) {
        return BeanMapper.map(clusterFilterE, ClusterFilterDO.class);
    }

    public List<ClusterFilterDO> entityToDatas(List<ClusterFilterE> clusterFilterES) {
        return Optional.ofNullable(clusterFilterES).orElse(Arrays.asList())
                .stream()
                .map(this::entityToData)
                .collect(Collectors.toList());
    }

}
