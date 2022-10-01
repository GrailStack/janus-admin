package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.entity.PublishIpE;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:59
 * @desc
 */
@Port
public interface PublishIpPort {

    void save(PublishIpE publishIpE);

    PublishIpE findPublishIpE(Long id);

    int updateStatus(Long id, ReleaseStatusEnum status);

    List<PublishIpE> findPublishIpEListByPublishId(Long publishId, ReleaseTypeEnum type);

    int batchUpdatePublishIpStatus(List<Long> publishIpIdList, ReleaseStatusEnum status);

}
