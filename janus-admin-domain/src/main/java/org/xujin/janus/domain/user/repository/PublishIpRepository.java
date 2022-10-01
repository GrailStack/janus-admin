package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.ports.PublishIpPort;

import javax.annotation.Resource;

/**
 * @author jin.xu
 * @date 2019/5/14
 */
@DomainRepository
public class PublishIpRepository {

    @Resource
    private PublishIpPort publishIpPort;

    public void save(PublishIpE publishIpE) {
        publishIpPort.save(publishIpE);
    }

    public PublishIpE findPublishIpE(Long id) {
        PublishIpE publishIpE = publishIpPort.findPublishIpE(id);
        if (publishIpE == null) {
            return null;
        }
        publishIpE.setPublishIpRepository(this);
        return publishIpE;
    }

    public int updateStatus(Long id, ReleaseStatusEnum status) {
        return publishIpPort.updateStatus(id, status);
    }

}
