package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.InstanceE;
import org.xujin.janus.domain.user.ports.InstancePort;
import org.xujin.janus.infrastructure.converter.InstanceConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.InstanceMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Adapter
public class InstanceAdapter implements InstancePort {

    @Autowired
    private InstanceMapper instanceMapper;

    @Autowired
    private InstanceConverter instanceConverter;

    @Override
    public void add(InstanceE instanceE) {
        instanceMapper.insert(instanceConverter.entityToData(instanceE));
    }
}
