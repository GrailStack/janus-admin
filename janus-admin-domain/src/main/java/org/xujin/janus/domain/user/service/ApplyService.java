package org.xujin.janus.domain.user.service;

import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.ApplyE;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:15
 * @desc
 */
public interface ApplyService {

    ApplyE findById(Long id);

    boolean updateApplyStatus(Long id, ApplyStatusEnum status);

}
