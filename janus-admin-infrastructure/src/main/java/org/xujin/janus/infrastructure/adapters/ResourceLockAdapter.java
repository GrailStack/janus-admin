package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.lock.Resource;
import org.xujin.janus.domain.lock.port.ResourceLockPort;
import org.xujin.janus.infrastructure.tunnel.db.dao.ResourceLockMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ResourceLockDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/10 13:53
 **/
@Adapter
public class ResourceLockAdapter implements ResourceLockPort {

    @Autowired
    private ResourceLockMapper lockMapper;

    @Override
    public int insert(Resource resource) {
        ResourceLockDO lockDO = convert(resource);
        return lockMapper.insert(lockDO);
    }

    @Override
    public int delete(Resource resource) {
        UpdateWrapper<ResourceLockDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("resource_type", resource.getResourceType().getCode());
        updateWrapper.eq("resource_id", resource.getResourceId());
        return lockMapper.delete(updateWrapper);
    }

    private ResourceLockDO convert(Resource resource) {
        ResourceLockDO lockDO = new ResourceLockDO();
        lockDO.setResourceType(resource.getResourceType());
        lockDO.setResourceId(resource.getResourceId());
        lockDO.setOperation(resource.getOperation());
        return lockDO;
    }

}
