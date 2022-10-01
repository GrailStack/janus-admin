package org.xujin.janus.app.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.AddPublishIpCmd;
import org.xujin.janus.app.command.co.PublishIpDetailCO;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.factory.PublishIpFactory;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishIpDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:56
 * @desc
 */
@Slf4j
@Component
public class PublishIpClientConverter implements ConverterI {
    @Resource
    private PublishIpFactory publishIpFactory;


    public PublishIpE addCmdToEntity(AddPublishIpCmd cmd) {
        PublishIpE publishIpE = publishIpFactory.createPublishIpEntity();
        BeanUtils.copyProperties(cmd, publishIpE);
        return publishIpE;
    }

    public PublishIpDetailCO dataToClient(PublishIpDO publishIpDO) {
        return BeanMapper.map(publishIpDO, PublishIpDetailCO.class);
    }

    public List<PublishIpDetailCO> dataToClients(List<PublishIpDO> publishIpDO) {
        return Optional.ofNullable(publishIpDO).orElse(Arrays.asList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }

}
