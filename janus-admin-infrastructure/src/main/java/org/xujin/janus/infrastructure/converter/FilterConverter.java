package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:33
 * @desc
 */
@Component
public class FilterConverter implements ConverterI {

    public FilterDO entityToData(FilterE filterE) {
        if (null == filterE) {
            return null;
        }
        return BeanMapper.map(filterE, FilterDO.class);
    }

    public FilterE dataToEntity(FilterDO filterDO) {
        if (null == filterDO) {
            return null;
        }
        return BeanMapper.map(filterDO, FilterE.class);
    }
}
