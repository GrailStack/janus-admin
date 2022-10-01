package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.api.port.OutServiceDraftPort;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.xujin.janus.infrastructure.converter.OutServiceConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.OutServiceDraftMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceDraftDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:23
 **/
@Adapter
public class OutServiceDraftAdapter implements OutServiceDraftPort {

    @Autowired
    private OutServiceDraftMapper draftMapper;

    @Autowired
    private OutServiceConverter converter;

    @Override
    public int deleteByApiDraftId(Long apiDraftId) {
        return draftMapper.deleteByApiDraftId(apiDraftId);
    }

    @Override
    public boolean add(String clusterCode, Long apiDraftId, OutServiceVO outService) {
        OutServiceDraftDO draftDO = converter.convertToDraftDo(outService);
        draftDO.setApiDraftId(apiDraftId);
        draftDO.setClusterCode(clusterCode);
        return draftMapper.insert(draftDO) > 0;
    }

    @Override
    public List<OutServiceVO> findByApiDraftId(Long apiDraftId) {
        List<OutServiceDraftDO> outServiceDraftDOList = draftMapper.findByApiDraftId(apiDraftId);
        return converter.convertForDraftList(outServiceDraftDOList);
    }

    @Override
    public Map<Long, List<OutServiceVO>> findByApiDraftIdList(List<Long> apiDraftIdList) {
        QueryWrapper<OutServiceDraftDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("api_draft_id", apiDraftIdList);
        queryWrapper.orderByAsc("id");
        List<OutServiceDraftDO> doList = draftMapper.selectList(queryWrapper);

        return doList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                OutServiceDraftDO::getApiDraftId,
                                Collectors.mapping(converter::convertForDraft, Collectors.toList())
                        )
                );
    }

}

