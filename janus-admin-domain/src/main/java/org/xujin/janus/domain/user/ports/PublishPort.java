package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.user.entity.PublishE;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:59
 * @desc
 */
@Port
public interface PublishPort {


    void save(PublishE publishE);

    PublishE findPublishE(Long id);

    int updateStatus(Long id, ReleaseStatusEnum status);

}
