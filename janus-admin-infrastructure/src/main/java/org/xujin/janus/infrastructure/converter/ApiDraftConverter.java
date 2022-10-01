package org.xujin.janus.infrastructure.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiDraftDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 16:50
 **/
@Component
public class ApiDraftConverter implements ConverterI {

    public ApiDraftDO entityToData(ApiDraftE draftE) {
        Validate.notNull(draftE, "draftE缺失");
        return BeanMapper.map(draftE, ApiDraftDO.class);
    }

    public ApiDraftE dataToEntity(ApiDraftDO draftDO) {
        if (draftDO == null) {
            return null;
        }
        return BeanMapper.map(draftDO, ApiDraftE.class);
    }

    public List<ApiDraftE> dataToEntity(List<ApiDraftDO> draftDOList) {
        if (CollectionUtils.isEmpty(draftDOList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(draftDOList, ApiDraftDO.class, ApiDraftE.class);
    }

}
