package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.repository.ApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:57
 * @desc
 */
@Factory
public class ApplyFactory {

    @Autowired
    private ApplyRepository applyRepository;

    public ApplyE createApplyE() {
        ApplyE applyE = new ApplyE();
        applyE.setApplyRepository(applyRepository);
        return applyE;
    }

    public ApplyE createApplyE(Long applyId) {
        return applyRepository.findApplyE(applyId);
    }

}
