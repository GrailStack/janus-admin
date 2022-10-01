package org.xujin.janus.app.converter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.AddClusterConfigCmd;
import org.xujin.janus.app.command.cmo.DeleteClusterConfigCmd;
import org.xujin.janus.app.command.cmo.UpdateClusterConfigCmd;
import org.xujin.janus.app.command.co.ClusterConfigDetailCO;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.entity.ClusterConfigE;
import org.xujin.janus.domain.user.factory.ClusterConfigFactory;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/5/27 10:02
 * @desc
 */
@Slf4j
@Component
public class ClusterConfigClientConverter implements ConverterI {

    @Autowired
    private ClusterConfigFactory clusterConfigFactory;

    @Autowired
    private ClusterMapper clusterMapper;

    public ClusterConfigE addCmdToEntity(AddClusterConfigCmd cmd) {
        ClusterConfigE clusterConfigE = clusterConfigFactory.createClusterConfigE();
        BeanUtils.copyProperties(cmd,clusterConfigE);
        return clusterConfigE;
    }

    public ClusterConfigE updateCmdToEntity(UpdateClusterConfigCmd cmd) {
        ClusterConfigE clusterConfigE = clusterConfigFactory.createClusterConfigE();
        BeanUtils.copyProperties(cmd,clusterConfigE);
        return clusterConfigE;
    }

    public ClusterConfigE deleteCmdToEntity(DeleteClusterConfigCmd cmd) {
        ClusterConfigE clusterConfigE = clusterConfigFactory.createClusterConfigE();
        BeanUtils.copyProperties(cmd,clusterConfigE);
        return clusterConfigE;
    }

    public ClusterConfigDetailCO dataToClient(ClusterConfigDO clusterConfigDO) {
        ClusterConfigDetailCO clusterConfigDetailCO = BeanMapper.map(clusterConfigDO, ClusterConfigDetailCO.class);
        if (null != clusterConfigDetailCO && !StringUtils.isEmpty(clusterConfigDetailCO.getClusterCode())) {
            QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("code", clusterConfigDetailCO.getClusterCode());
            queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
            ClusterDO clusterDO = clusterMapper.selectOne(queryWrapper);
            if (null != clusterDO && !StringUtils.isEmpty(clusterDO.getName())) {
                clusterConfigDetailCO.setClusterName(clusterDO.getName());
            }
        }
        return clusterConfigDetailCO;
    }

    public List<ClusterConfigDetailCO> dataToClients(List<ClusterConfigDO> clusterConfigDOS) {
        return Optional.ofNullable(clusterConfigDOS).orElse(Lists.newArrayList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }
}
