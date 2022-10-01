package org.xujin.janus.infrastructure.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishIpDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 16:03
 * @desc
 */
@Component
public class PublishIpConverter implements ConverterI {

    public List<PublishIpDO> entityToData(PublishIpE publishIpE) {
        List<PublishIpDO> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(publishIpE.getIps())) {
            PublishIpDO publishIpDO = new PublishIpDO();
            publishIpDO.setIp(publishIpE.getIp());
            publishIpDO.setPublishId(publishIpE.getPublishId());
            publishIpDO.setType(publishIpE.getType());
            publishIpDO.setCreator(publishIpE.getCreator());
            publishIpDO.setModifier(publishIpE.getModifier());
            publishIpDO.setStatus(publishIpE.getStatus());
            list.add(publishIpDO);
            return list;
        }

        publishIpE.getIps().forEach(x -> {
            PublishIpDO publishIpDO = new PublishIpDO();
            publishIpDO.setIp(x);
            publishIpDO.setPublishId(publishIpE.getPublishId());
            publishIpDO.setType(publishIpE.getType());
            publishIpDO.setCreator(publishIpE.getCreator());
            publishIpDO.setModifier(publishIpE.getModifier());
            publishIpDO.setStatus(publishIpE.getStatus());
            list.add(publishIpDO);
        });
        return list;
    }

    public PublishIpE dataToEntity(PublishIpDO publishIpDO) {
        return BeanMapper.map(publishIpDO, PublishIpE.class);
    }

    public List<PublishIpE> dataToEntities(List<PublishIpDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(doList, PublishIpDO.class, PublishIpE.class);
    }

}
