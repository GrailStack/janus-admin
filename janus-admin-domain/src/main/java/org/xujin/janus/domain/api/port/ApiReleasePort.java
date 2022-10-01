package org.xujin.janus.domain.api.port;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.api.constant.ApiStatusEnum;
import org.xujin.janus.domain.api.entity.ApiReleaseE;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 14:19
 **/
@Port
public interface ApiReleasePort {

    boolean lock(Long id, Integer currVersion, String lockType);

    boolean releaseLock(Long id, Integer currVersion, String currLockType);

    ApiReleaseE findById(Long id);

    boolean add(ApiReleaseE releaseE);

    boolean updateWithReleaseLock(ApiReleaseE releaseE, String lockType);

    boolean updateStatusWithReleaseLock(Long id, Integer currVersion, ApiStatusEnum status, String lockType);

    boolean deleteWithReleaseLockCheck(Long id, Integer currVersion, String lockType);

    boolean containsNameInCluster(String clusterCode, String name);

    boolean containsApiInCluster(String clusterCode, String path, String method);

}
