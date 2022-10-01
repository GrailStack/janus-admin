package org.xujin.janus.app.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.co.InstanceDetailCO;
import org.xujin.janus.domain.user.entity.AlarmE;
import org.xujin.janus.domain.user.factory.AlarmFactory;
import org.xujin.janus.domain.user.repository.ClusterFilterRepository;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

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
public class InstanceClientConverter implements ConverterI {

    public InstanceDetailCO dataToClient(InstanceDO instanceDO) {
        return BeanMapper.map(instanceDO, InstanceDetailCO.class);
    }

    public List<InstanceDetailCO> dataToClients(List<InstanceDO> instanceDOS) {
        return Optional.ofNullable(instanceDOS).orElse(Arrays.asList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }


}
