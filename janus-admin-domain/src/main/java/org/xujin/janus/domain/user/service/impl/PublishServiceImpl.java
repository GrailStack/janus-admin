package org.xujin.janus.domain.user.service.impl;

import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.ports.PublishIpPort;
import org.xujin.janus.domain.user.service.PublishService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/18 10:59
 **/
@DomainService
public class PublishServiceImpl implements PublishService {

    @Autowired
    private PublishIpPort publishIpPort;

    @Override
    public List<PublishIpE> findPublishIpEList(Long publishId, ReleaseTypeEnum type) {
        return publishIpPort.findPublishIpEListByPublishId(publishId, type);
    }

    @Override
    public int batchUpdatePublishIpStatus(List<Long> publishIpIdList, ReleaseStatusEnum status) {
        if (CollectionUtils.isEmpty(publishIpIdList)) {
            return 0;
        }
        return publishIpPort.batchUpdatePublishIpStatus(publishIpIdList, status);
    }

    @Override
    public boolean isLogicalCompletedStatus(ReleaseStatusEnum status) {
        return ReleaseStatusEnum.COMPLETED == status ||
                ReleaseStatusEnum.IGNORED == status ||
                ReleaseStatusEnum.RELEASE_SUCCESS == status;
    }

}
