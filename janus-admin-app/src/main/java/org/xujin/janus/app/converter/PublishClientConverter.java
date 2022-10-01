package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.AddPublishCmd;
import org.xujin.janus.app.command.co.PublishDetailCO;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.xujin.janus.domain.user.factory.PublishFactory;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishDO;
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
public class PublishClientConverter implements ConverterI {
    @Resource
    private PublishFactory publishFactory;


    public PublishE addCmdToEntity(AddPublishCmd cmd) {
        PublishE publishE = publishFactory.createPublishEntity();
        BeanUtils.copyProperties(cmd,publishE);
        return publishE;
    }

    public PublishDetailCO dataToClient(PublishDO publishDO) {
        return BeanMapper.map(publishDO, PublishDetailCO.class);
    }

    public List<PublishDetailCO> dataToClients(List<PublishDO> publishDOs) {
        return Optional.ofNullable(publishDOs).orElse(Arrays.asList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }

}
