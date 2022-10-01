package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/5/27 14:32
 * @desc
 */
@Component
public class PublishConverter implements ConverterI {

    public PublishDO entityToData(PublishE publishE) {
        return BeanMapper.map(publishE, PublishDO.class);
    }

    public PublishE dataToEntity(PublishDO publishDO) {
        return BeanMapper.map(publishDO, PublishE.class);
    }

}
