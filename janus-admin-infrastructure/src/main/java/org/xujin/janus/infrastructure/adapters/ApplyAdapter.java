package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.ports.ApplyPort;
import org.xujin.janus.infrastructure.converter.ApplyConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApplyMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 16:02
 * @desc
 */
@Adapter
public class ApplyAdapter implements ApplyPort {
    @Resource
    private ApplyMapper mapper;
    @Resource
    private ApplyConverter applyConverter;

    @Override
    public void add(ApplyE applyE) {
        ApplyDO applyDO = applyConverter.entityToData(applyE);
        mapper.insert(applyDO);
        applyE.setId(applyDO.getId());
    }

    @Override
    public void delete(ApplyE applyE) {
        ApplyDO applyDO = applyConverter.entityToData(applyE);
        mapper.updateById(applyDO);
    }

    @Override
    public void update(ApplyE applyE) {
        ApplyDO applyDO = applyConverter.entityToData(applyE);
        mapper.updateById(applyDO);
    }

    @Override
    public ApplyE findById(Long id) {
        return applyConverter.dataToEntity(mapper.selectById(id));
    }

    @Override
    public int updateStatus(Long id, ApplyStatusEnum status) {
        UpdateWrapper<ApplyDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status.getCode());
        updateWrapper.eq("id", id);
        return mapper.update(null, updateWrapper);
    }

    @Override
    public int updateStatus(Long id, ApplyStatusEnum status, String approver) {
        UpdateWrapper<ApplyDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status.getCode());
        updateWrapper.set("approver", approver);
        updateWrapper.eq("id", id);
        return mapper.update(null, updateWrapper);
    }

    @Override
    public int updateChanges(Long id, String changes) {
        UpdateWrapper<ApplyDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("changes", changes);
        updateWrapper.eq("id", id);
        return mapper.update(null, updateWrapper);
    }

    @Override
    public int updateApplyStatus(Long id, ApplyStatusEnum status) {
        return mapper.updateStatus(id, status.getCode());
    }

}
