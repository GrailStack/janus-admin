package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.co.ApiDraftCO;
import org.xujin.janus.app.command.co.ApiReleaseCO;
import org.xujin.janus.domain.api.valueobject.OutServiceConfig;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiDraftDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceDraftDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 12:18
 **/
@Component
public class ApiClientConverter {

    @Autowired
    private OutServiceClientConverter outServiceClientConverter;

    public ApiReleaseCO convertToApiReleaseCO(ApiReleaseDO releaseDO, List<OutServiceReleaseDO> outServiceReleaseDOList) {
        ApiReleaseCO apiReleaseCO = BeanMapper.map(releaseDO, ApiReleaseCO.class);

        List<OutServiceVO> outServices = outServiceClientConverter.convertForReleaseList(outServiceReleaseDOList);
        apiReleaseCO.setOutServiceConfig(new OutServiceConfig().setOutServices(outServices));

        return apiReleaseCO;
    }

    public ApiReleaseCO convertToApiReleaseCO(ApiReleaseDO releaseDO) {
        return BeanMapper.map(releaseDO, ApiReleaseCO.class);
    }

    public List<ApiReleaseCO> convertToApiReleaseCOList(List<ApiReleaseDO> releaseDOList) {
        if (CollectionUtils.isEmpty(releaseDOList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(releaseDOList, ApiReleaseDO.class, ApiReleaseCO.class);
    }

    public ApiDraftCO convertToApiDraftCO(ApiDraftDO draftDO) {
        return BeanMapper.map(draftDO, ApiDraftCO.class);
    }

    public List<ApiDraftCO> convertToApiDraftCOList(List<ApiDraftDO> draftDOList) {
        if (CollectionUtils.isEmpty(draftDOList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(draftDOList, ApiDraftDO.class, ApiDraftCO.class);
    }

    public ApiDraftCO convertToApiDraftCO(ApiDraftDO apiDraftDO, List<OutServiceDraftDO> outServiceDraftDOList) {
        ApiDraftCO apiDraftCO = convertToApiDraftCO(apiDraftDO);

        List<OutServiceVO> outServices = outServiceClientConverter.convertForDraftList(outServiceDraftDOList);
        apiDraftCO.setOutServiceConfig(new OutServiceConfig().setOutServices(outServices));

        return apiDraftCO;
    }

}
