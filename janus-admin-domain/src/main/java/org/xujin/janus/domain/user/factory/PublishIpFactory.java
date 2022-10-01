package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.repository.PublishIpRepository;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:57
 * @desc
 */
@Factory
public class PublishIpFactory {

    @Resource
    private PublishIpRepository publishIpRepository;

    public PublishIpE createPublishIpEntity() {
        PublishIpE publishIpE = new PublishIpE();
        publishIpE.setPublishIpRepository(publishIpRepository);
        return publishIpE;
    }

    public PublishIpE findPublishIpE(Long id) {
        return publishIpRepository.findPublishIpE(id);
    }

}
