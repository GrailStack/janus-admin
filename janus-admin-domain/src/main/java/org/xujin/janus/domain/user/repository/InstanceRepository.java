package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.entity.InstanceE;
import org.xujin.janus.domain.user.ports.InstancePort;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xujin
 */
@DomainRepository
public class InstanceRepository {

    @Autowired
    private InstancePort instancePort;

    public void save(InstanceE applyE) {
        instancePort.add(applyE);
    }


}
