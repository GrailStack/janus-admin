package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.ports.ApplyPort;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:57
 * @desc
 */
@DomainRepository
public class ApplyRepository {
    @Resource
    ApplyPort applyPort;

    public void save(ApplyE applyE) {
        applyPort.add(applyE);
    }

    public void delete(ApplyE applyE) {
        applyPort.delete(applyE);
    }

    public void update(ApplyE applyE) {
        applyPort.update(applyE);
    }

    public ApplyE findApplyE(Long applyId) {
        ApplyE applyE = applyPort.findById(applyId);
        if (applyE == null) {
            return null;
        }
        applyE.setApplyRepository(this);
        return applyE;
    }

    public boolean updateStatus(Long id, ApplyStatusEnum status) {
        return applyPort.updateStatus(id, status) > 0;
    }

    public boolean updateStatus(Long id, ApplyStatusEnum status, String approver) {
        return applyPort.updateStatus(id, status, approver) > 0;
    }

    public boolean updateChanges(Long id, String changes) {
        return applyPort.updateChanges(id, changes) > 0;
    }

}
