package org.xujin.janus.domain.api.port;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:22
 **/
@Port
public interface OutServiceReleasePort {

    boolean add(String clusterCode, Long apiReleaseId, OutServiceVO outService);

    int deleteByApiReleaseId(Long apiReleaseId);

    List<OutServiceVO> findByApiReleaseId(Long apiReleaseId);

}
