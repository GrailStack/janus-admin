package org.xujin.janus.domain.api.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.api.port.OutServiceReleasePort;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:22
 **/
@DomainRepository
public class OutServiceReleaseRepository {

    @Autowired
    private OutServiceReleasePort releasePort;

    public int deleteByApiReleaseId(Long apiReleaseId) {
        return releasePort.deleteByApiReleaseId(apiReleaseId);
    }

    public boolean add(String clusterCode, Long apiReleaseId, OutServiceVO outService) {
        return releasePort.add(clusterCode, apiReleaseId, outService);
    }

    public List<OutServiceVO> findByApiReleaseId(Long apiReleaseId) {
        return releasePort.findByApiReleaseId(apiReleaseId);
    }

}
