package org.xujin.janus.domain.user.service;

import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.entity.PublishIpE;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/18 10:58
 **/
public interface PublishService {

    List<PublishIpE> findPublishIpEList(Long publishId, ReleaseTypeEnum type);

    int batchUpdatePublishIpStatus(List<Long> publishIpIdList, ReleaseStatusEnum status);

    boolean isLogicalCompletedStatus(ReleaseStatusEnum status);

}
