package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.app.command.cmo.UpdateAlarmCmd;
import org.xujin.janus.app.command.co.AlarmDetailCO;
import org.xujin.janus.domain.user.entity.AlarmE;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.entity.ClusterFilterE;
import org.xujin.janus.domain.user.factory.AlarmFactory;
import org.xujin.janus.domain.user.factory.ClusterFactory;
import org.xujin.janus.domain.user.repository.ClusterFilterRepository;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
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
public class AlarmClientConverter implements ConverterI {

    @Resource
    AlarmFactory alarmFactory;
    @Resource
    ClusterFilterRepository clusterFilterRepository;

    public Byte deleted = 1;

    public AlarmE updateCmdToEntity(UpdateAlarmCmd cmd) {
        AlarmE alarmEntity = alarmFactory.createAlarmEntity();
        BeanUtils.copyProperties(cmd,alarmEntity);
        return alarmEntity;
    }

    public AlarmDetailCO dataToClient(AlarmDO alarmDO) {
        return BeanMapper.map(alarmDO, AlarmDetailCO.class);
    }

    public List<AlarmDetailCO> dataToClients(List<AlarmDO> alarmDOS) {
        return Optional.ofNullable(alarmDOS).orElse(Arrays.asList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }


}
