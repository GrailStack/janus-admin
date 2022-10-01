package org.xujin.janus.domain.lock;

import org.xujin.janus.domain.lock.constant.ResourceTypeEnum;
import org.xujin.janus.domain.lock.repository.ResourceLockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/10 11:29
 **/
@Slf4j
@Service
public class ResourceLockService {

    @Autowired
    private ResourceLockRepository repository;

    @Transactional(rollbackFor = Throwable.class)
    public boolean lockCluster(String clusterCode) {
        return lock(ResourceTypeEnum.CLUSTER, clusterCode);
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean unLockCluster(String clusterCode) {
        return unlock(ResourceTypeEnum.CLUSTER, clusterCode);
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean lock(ResourceTypeEnum resourceType, String resourceId) {
        Resource resource = assembleResource(resourceType, resourceId, null);
        return lock(resource);
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean lock(ResourceTypeEnum resourceType, String resourceId, String operation) {
        Resource resource = assembleResource(resourceType, resourceId, operation);
        return lock(resource);
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean lock(Resource resource) {
        try {
            return repository.insert(resource) > 0;
        } catch (Exception ex) {
            log.warn("获取资源锁失败，resource: {}", resource, ex);
        }
        return false;
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean unlock(ResourceTypeEnum resourceType, String resourceId) {
        Resource resource = assembleResource(resourceType, resourceId, null);
        return unlock(resource);
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean unlock(Resource resource) {
        try {
            return repository.delete(resource) > 0;
        } catch (Exception ex) {
            log.warn("释放资源锁失败，resource: {}", resource, ex);
            throw ex;
        }
    }

    private Resource assembleResource(ResourceTypeEnum resourceTypeEnum, String resourceId, String operation) {
        Resource resource = new Resource();
        resource.setResourceType(resourceTypeEnum);
        resource.setResourceId(resourceId);
        resource.setOperation(operation);
        return resource;
    }

}
