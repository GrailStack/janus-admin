package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.co.AuditLogDetailCO;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.factory.FilterFactory;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AuditLogDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:15
 * @desc
 */
@Slf4j
@Component
public class AuditLogClientConverter implements ConverterI {


    public AuditLogDetailCO dataToClient(AuditLogDO auditLogDO) {
        AuditLogDetailCO map = BeanMapper.map(auditLogDO, AuditLogDetailCO.class);
        return map;
    }

    public List<AuditLogDetailCO> dataToClients(List<AuditLogDO> filterDOS) {
        return Optional.ofNullable(filterDOS).orElse(Lists.newArrayList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }
}
