package org.xujin.janus.domain.api.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.api.constant.ApiStatusEnum;
import org.xujin.janus.domain.api.entity.ApiReleaseE;
import org.xujin.janus.domain.api.port.ApiReleasePort;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/5/29 14:57
 **/
@DomainRepository
public class ApiReleaseRepository {

    @Autowired
    private ApiReleasePort releasePort;

    public ApiReleaseE finApiInReleaseE(Long id) {
        ApiReleaseE releaseE = releasePort.findById(id);
        if (releaseE == null) {
            return null;
        }
        releaseE.setReleaseRepository(this);
        return releaseE;
    }

    public boolean add(ApiReleaseE releaseE) {
        return releasePort.add(releaseE);
    }

    public boolean updateWithReleaseLock(ApiReleaseE releaseE, String lockType) {
        return releasePort.updateWithReleaseLock(releaseE, lockType);
    }

    public boolean updateStatusWithReleaseLock(Long id, Integer currVersion, ApiStatusEnum status, String lockType) {
        return releasePort.updateStatusWithReleaseLock(id, currVersion, status, lockType);
    }

    public boolean deleteWithReleaseLockCheck(Long id, Integer currVersion, String lockType) {
        return releasePort.deleteWithReleaseLockCheck(id, currVersion, lockType);
    }

    public boolean containsNameInCluster(String clusterCode, String name) {
        return releasePort.containsNameInCluster(clusterCode, name);
    }

    public boolean containsApiInCluster(String clusterCode, String path, String method) {
        return releasePort.containsApiInCluster(clusterCode, path, method);
    }

    public boolean lock(Long id, Integer currVersion, String lockType) {
        return releasePort.lock(id, currVersion, lockType);
    }

    public boolean releaseLock(Long id, Integer currVersion, String currLockType) {
        return releasePort.releaseLock(id, currVersion, currLockType);
    }

}
