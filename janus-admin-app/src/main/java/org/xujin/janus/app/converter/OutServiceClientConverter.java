package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceDraftDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/8 16:57
 **/
@Component
public class OutServiceClientConverter {

    public OutServiceVO convertForRelease(OutServiceReleaseDO releaseDO) {
        return BeanMapper.map(releaseDO, OutServiceVO.class);
    }

    public List<OutServiceVO> convertForReleaseList(List<OutServiceReleaseDO> releaseDOList) {
        if (CollectionUtils.isEmpty(releaseDOList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(releaseDOList, OutServiceReleaseDO.class, OutServiceVO.class);
    }

    public OutServiceVO convertForDraft(OutServiceDraftDO draftDO) {
        return BeanMapper.map(draftDO, OutServiceVO.class);
    }

    public List<OutServiceVO> convertForDraftList(List<OutServiceDraftDO> draftDOList) {
        if (CollectionUtils.isEmpty(draftDOList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(draftDOList, OutServiceDraftDO.class, OutServiceVO.class);
    }

}
