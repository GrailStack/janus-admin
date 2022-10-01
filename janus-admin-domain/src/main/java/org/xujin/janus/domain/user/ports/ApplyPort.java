package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.ApplyE;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:59
 * @desc
 */
@Port
public interface ApplyPort {

    void add(ApplyE applyE);

    void delete(ApplyE applyE);

    void update(ApplyE applyE);

    ApplyE findById(Long id);

    int updateStatus(Long id, ApplyStatusEnum status);

    int updateStatus(Long id, ApplyStatusEnum status, String approver);

    int updateChanges(Long id, String changes);

    int updateApplyStatus(Long id, ApplyStatusEnum status);

}
