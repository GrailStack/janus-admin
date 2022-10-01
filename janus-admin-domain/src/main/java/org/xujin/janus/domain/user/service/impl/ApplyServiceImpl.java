package org.xujin.janus.domain.user.service.impl;

import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.ports.ApplyPort;
import org.xujin.janus.domain.user.service.ApplyService;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:15
 * @desc
 */
@DomainService
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private ApplyPort applyPort;

    @Override
    public ApplyE findById(Long id) {
        return applyPort.findById(id);
    }

    @Override
    public boolean updateApplyStatus(Long id, ApplyStatusEnum status) {
        return applyPort.updateApplyStatus(id, status) > 0;
    }

}
