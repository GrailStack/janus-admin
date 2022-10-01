package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.api.entity.ApiReleaseE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 16:50
 **/
@Component
public class ApiReleaseConverter implements ConverterI {

    public ApiReleaseE dataToEntity(ApiReleaseDO releaseDO) {
        if (releaseDO == null) {
            return null;
        }
        return BeanMapper.map(releaseDO, ApiReleaseE.class);
    }

    public ApiReleaseDO entityToData(ApiReleaseE releaseE) {
        Validate.notNull(releaseE, "releaseE缺失");
        return BeanMapper.map(releaseE, ApiReleaseDO.class);
    }

}
