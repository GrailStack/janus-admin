package org.xujin.janus.infrastructure.converter;

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
 * @date 2020/6/6 19:16
 **/
@Component
public class OutServiceConverter {

    public OutServiceDraftDO convertToDraftDo(OutServiceVO vo) {
        return BeanMapper.map(vo, OutServiceDraftDO.class);
    }

    public OutServiceReleaseDO convertToReleaseDo(OutServiceVO vo) {
        return BeanMapper.map(vo, OutServiceReleaseDO.class);
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

    public List<OutServiceVO> convertForReleaseList(List<OutServiceReleaseDO> releaseDOList) {
        if (CollectionUtils.isEmpty(releaseDOList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(releaseDOList, OutServiceReleaseDO.class, OutServiceVO.class);
    }

}
