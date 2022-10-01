package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.ports.PublishPort;

import javax.annotation.Resource;

/**
 * @author jin.xu
 * @date 2019/5/14
 */
@DomainRepository
public class PublishRepository {
    @Resource
    private PublishPort publishPort;

    public void save(PublishE publishE) {
        publishPort.save(publishE);
    }

    public PublishE findPublishE(Long id) {
        PublishE publishE = publishPort.findPublishE(id);
        if (publishE == null) {
            return null;
        }
        publishE.setPublishRepository(this);
        return publishE;
    }

    public boolean updateStatus(Long id, ReleaseStatusEnum status) {
        return publishPort.updateStatus(id, status) > 0;
    }

}
