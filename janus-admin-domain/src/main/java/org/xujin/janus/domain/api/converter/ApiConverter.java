package org.xujin.janus.domain.api.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.valueobject.ApiDraftVO;
import org.xujin.janus.domain.api.valueobject.OutServiceConfig;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 20:52
 **/
@Component
public class ApiConverter {

    public ApiDraftVO convertDraftToVo(ApiDraftE draftE, List<OutServiceVO> outServices) {
        ApiDraftVO draftVO = BeanMapper.map(draftE, ApiDraftVO.class);
        draftVO.setStatusDesc(draftVO.getStatus() == null ? null : draftVO.getStatus().getDesc());
        draftVO.setOperateTypeDesc(draftVO.getOperateType() == null ? null : draftVO.getOperateType().getDesc());

        OutServiceConfig outServiceConfig = new OutServiceConfig();
        outServiceConfig.setOutServices(outServices != null ? outServices : Lists.newArrayList());
        draftVO.setOutServiceConfig(outServiceConfig);

        return draftVO;
    }

}
