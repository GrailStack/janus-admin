package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.api.port.OutServiceReleasePort;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.xujin.janus.infrastructure.converter.OutServiceConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.OutServiceReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:23
 **/
@Adapter
public class OutServiceReleaseAdapter implements OutServiceReleasePort {

    @Autowired
    public OutServiceReleaseMapper releaseMapper;

    @Autowired
    private OutServiceConverter converter;

    @Override
    public int deleteByApiReleaseId(Long apiReleaseId) {
        return releaseMapper.deleteByApiReleaseId(apiReleaseId);
    }

    @Override
    public boolean add(String clusterCode, Long apiReleaseId, OutServiceVO outService) {
        OutServiceReleaseDO releaseDO = converter.convertToReleaseDo(outService);
        releaseDO.setApiReleaseId(apiReleaseId);
        releaseDO.setClusterCode(clusterCode);
        return releaseMapper.insert(releaseDO) > 0;
    }

    @Override
    public List<OutServiceVO> findByApiReleaseId(Long apiReleaseId) {
        List<OutServiceReleaseDO> outServiceDraftDOList = releaseMapper.findByApiReleaseId(apiReleaseId);
        return converter.convertForReleaseList(outServiceDraftDOList);
    }

}
