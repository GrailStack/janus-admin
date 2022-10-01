package org.xujin.janus.domain.lock.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.lock.Resource;
import org.xujin.janus.domain.lock.port.ResourceLockPort;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/10 13:44
 **/
@DomainRepository
public class ResourceLockRepository {

    @Autowired
    private ResourceLockPort lockPort;

    public int insert(Resource resource) {
        return lockPort.insert(resource);
    }

    public int delete(Resource resource) {
        return lockPort.delete(resource);
    }

}
