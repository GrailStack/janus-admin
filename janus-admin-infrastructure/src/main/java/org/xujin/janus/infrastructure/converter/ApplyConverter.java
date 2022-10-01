package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/5/26 16:03
 * @desc
 */
@Component
public class ApplyConverter implements ConverterI {
    public ApplyDO entityToData(ApplyE applyE) {
        if (null == applyE) {
            return null;
        }
        ApplyDO applyDO = BeanMapper.map(applyE, ApplyDO.class);
        return applyDO;
    }

    public ApplyE dataToEntity(ApplyDO applyDO) {
        return BeanMapper.map(applyDO, ApplyE.class);
    }
}
