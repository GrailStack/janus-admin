package org.xujin.janus.domain.api.port;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:22
 **/
@Port
public interface OutServiceDraftPort {

    int deleteByApiDraftId(Long apiDraftId);

    boolean add(String clusterCode, Long apiDraftId, OutServiceVO outService);

    List<OutServiceVO> findByApiDraftId(Long apiDraftId);

    Map<Long, List<OutServiceVO>> findByApiDraftIdList(List<Long> apiDraftIdList);

}
