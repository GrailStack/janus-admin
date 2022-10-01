package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.api.constant.ApiStatusEnum;
import org.xujin.janus.domain.api.entity.ApiReleaseE;
import org.xujin.janus.domain.api.port.ApiReleasePort;
import org.xujin.janus.infrastructure.converter.ApiReleaseConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 14:22
 **/
@Adapter
public class ApiReleaseAdapter implements ApiReleasePort {

    @Autowired
    private ApiReleaseMapper mapper;

    @Autowired
    private ApiReleaseConverter converter;

    @Override
    public boolean lock(Long id, Integer currVersion, String lockType) {
        UpdateWrapper<ApiReleaseDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("lock_type", lockType);
        updateWrapper.eq("id", id);
        updateWrapper.eq("version", currVersion);
        updateWrapper.eq("lock_type", "");
        return mapper.update(null, updateWrapper) > 0;
    }

    @Override
    public boolean releaseLock(Long id, Integer currVersion, String currLockType) {
        UpdateWrapper<ApiReleaseDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("lock_type", "");
        updateWrapper.eq("id", id);
        updateWrapper.eq("version", currVersion);
        updateWrapper.eq("lock_type", currLockType);
        return mapper.update(null, updateWrapper) > 0;
    }

    @Override
    public ApiReleaseE findById(Long id) {
        return converter.dataToEntity(mapper.findById(id));
    }

    @Override
    public boolean add(ApiReleaseE releaseE) {
        ApiReleaseDO releaseDO = converter.entityToData(releaseE);
        Validate.notNull(releaseDO.getId(), "id缺失");
        return mapper.insert(releaseDO) > 0;
    }

    @Override
    public boolean updateWithReleaseLock(ApiReleaseE releaseE, String lockType) {
        UpdateWrapper<ApiReleaseDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", releaseE.getId());
        updateWrapper.eq("version", releaseE.getVersion() - 1);
        updateWrapper.eq("lock_type", lockType);

        ApiReleaseDO releaseDO = converter.entityToData(releaseE);
        return mapper.update(releaseDO, updateWrapper) > 0;
    }

    @Override
    public boolean updateStatusWithReleaseLock(Long id, Integer currVersion, ApiStatusEnum status, String lockType) {
        UpdateWrapper<ApiReleaseDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status.getCode());
        updateWrapper.set("lock_type", "");
        updateWrapper.eq("id", id);
        updateWrapper.eq("version", currVersion);
        updateWrapper.eq("lock_type", lockType);
        return mapper.update(null, updateWrapper) > 0;
    }

    @Override
    @Transactional
    public boolean deleteWithReleaseLockCheck(Long id, Integer currVersion, String lockType) {
        boolean released = releaseLock(id, currVersion, lockType);
        if (!released) {
            return false;
        }
        return mapper.deleteWithCheck(id) == 1;
    }

    @Override
    public boolean containsNameInCluster(String clusterCode, String name) {
        return mapper.findByClusterCodeAndName(clusterCode, name) != null;
    }

    @Override
    public boolean containsApiInCluster(String clusterCode, String path, String method) {
        QueryWrapper<ApiReleaseDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cluster_code", clusterCode);
        queryWrapper.eq("path", path);
        queryWrapper.eq("method", method);
        queryWrapper.last("limit 1");
        return mapper.selectOne(queryWrapper) != null;
    }

}
