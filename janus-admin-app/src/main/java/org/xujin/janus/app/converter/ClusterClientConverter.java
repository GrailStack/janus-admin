package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.domain.user.entity.ClusterFilterE;
import org.xujin.janus.domain.user.repository.ClusterFilterRepository;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.factory.ClusterFactory;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApplyMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.InstanceMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.xujin.janus.app.command.cmo.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/14 18:10
 * @desc
 */
@Slf4j
@Component
public class ClusterClientConverter implements ConverterI {

    @Resource
    ClusterFactory clusterFactory;
    @Resource
    ClusterFilterRepository clusterFilterRepository;

    @Resource
    private AlarmMapper alarmMapper;

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private InstanceMapper instanceMapper;


    public Byte deleted = 1;

    public ClusterEntity addCmdToEntity(AddClusterCmd cmd) {
        ClusterEntity clusterEntity = clusterFactory.createClusterEntity();
        BeanUtils.copyProperties(cmd,clusterEntity);
        if (!StringUtils.isEmpty(cmd.getState())) {
            clusterEntity.setStatus(new Byte(cmd.getState()));
        }
        return clusterEntity;
    }

    public ClusterEntity deleteCmdToEntity(DeleteClusterCmd cmd) {
        ClusterEntity clusterEntity = clusterFactory.createClusterEntity();
        BeanUtils.copyProperties(cmd,clusterEntity);
        clusterEntity.setIsDeleted(deleted);
        clusterEntity.setUpdated(cmd.getCurrentUserId());
        return clusterEntity;
    }

    public ClusterEntity updateCmdToEntity(UpdateClusterCmd cmd) {
        ClusterEntity clusterEntity = clusterFactory.createClusterEntity();
        BeanUtils.copyProperties(cmd,clusterEntity);
        clusterEntity.setUpdated(cmd.getCurrentUserId());
        if (!StringUtils.isEmpty(cmd.getState())) {
            clusterEntity.setStatus(new Byte(cmd.getState()));
        }
        return clusterEntity;
    }

    public ClusterDetailCo dataToClient(ClusterDO clusterDO) {
        ClusterDetailCo detailCo = BeanMapper.map(clusterDO, ClusterDetailCo.class);
        detailCo.setState(clusterDO.getStatus().toString());
        //告警次数
        Integer alarm = alarmMapper.countByClusterCode(clusterDO.getCode());
        detailCo.setAlarm(alarm);

        //配置待审批数
        Integer change = applyMapper.countByClusterCode(clusterDO.getCode());
        detailCo.setChange(change);

        //实例数
        Integer instance = instanceMapper.countByClusterCode(clusterDO.getCode());
        detailCo.setInstance(instance);
        return detailCo;
    }

    public List<ClusterDetailCo> dataToClients(List<ClusterDO> clusterDOs) {
        return Optional.ofNullable(clusterDOs).orElse(Lists.newArrayList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }

    public List<ClusterFilterE> addToEntities(AddClusterFilterCmd addClusterFilterCmd) {
        List<ClusterFilterE> list = new ArrayList<>();
        List<BigInteger> filterIds = addClusterFilterCmd.getFilterIds();
        if (!CollectionUtils.isEmpty(filterIds)) {
            filterIds.forEach(x -> {
                ClusterFilterE clusterFilterE = new ClusterFilterE();
                clusterFilterE.setFilterId(x);
                clusterFilterE.setClusterCode(addClusterFilterCmd.getCode());
                clusterFilterE.setClusterFilterRepository(clusterFilterRepository);
                list.add(clusterFilterE);
            });
        } else {
            ClusterFilterE clusterFilterE = new ClusterFilterE();
            clusterFilterE.setClusterCode(addClusterFilterCmd.getCode());
            clusterFilterE.setClusterFilterRepository(clusterFilterRepository);
            list.add(clusterFilterE);
        }
        return list;
    }

    public List<ClusterFilterE> addToEntities(UpdateClusterFilterCmd updateClusterFilterCmd) {
        List<ClusterFilterE> list = new ArrayList<>();
        List<BigInteger> filterIds = updateClusterFilterCmd.getFilterIds();
        if (!CollectionUtils.isEmpty(filterIds)) {
            filterIds.forEach(x -> {
                ClusterFilterE clusterFilterE = new ClusterFilterE();
                clusterFilterE.setFilterId(x);
                clusterFilterE.setClusterCode(updateClusterFilterCmd.getCode());
                clusterFilterE.setClusterFilterRepository(clusterFilterRepository);
                list.add(clusterFilterE);
            });
        } else {
            ClusterFilterE clusterFilterE = new ClusterFilterE();
            clusterFilterE.setClusterCode(updateClusterFilterCmd.getCode());
            clusterFilterE.setClusterFilterRepository(clusterFilterRepository);
            list.add(clusterFilterE);
        }
        return list;
    }
}
