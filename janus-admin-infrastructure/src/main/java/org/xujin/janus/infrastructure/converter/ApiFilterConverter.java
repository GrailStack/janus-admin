package org.xujin.janus.infrastructure.converter;

import org.apache.commons.lang3.Validate;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.ApiFilterE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiFilterDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/6/17 13:30
 * @desc
 */
@Component
public class ApiFilterConverter implements ConverterI {

    public ApiFilterDO entityToData(ApiFilterE apiFilterE) {
        Validate.notNull(apiFilterE, "apiFliterE缺失");
        return BeanMapper.map(apiFilterE, ApiFilterDO.class);
    }

}
