package org.xujin.janus.domain.api.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.api.port.OutServiceDraftPort;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:22
 **/
@DomainRepository
public class OutServiceDraftRepository {

    @Autowired
    private OutServiceDraftPort draftPort;

    public int deleteByApiDraftId(Long apiDraftId) {
        return draftPort.deleteByApiDraftId(apiDraftId);
    }

    public boolean add(String clusterCode, Long apiDraftId, OutServiceVO outService) {
        return draftPort.add(clusterCode, apiDraftId, outService);
    }

    public List<OutServiceVO> findByApiDraftId(Long apiDraftId) {
        return draftPort.findByApiDraftId(apiDraftId);
    }

    public Map<Long, List<OutServiceVO>> findByApiDraftIdList(List<Long> apiDraftIdList) {
        return draftPort.findByApiDraftIdList(apiDraftIdList);
    }

}
