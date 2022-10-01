package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.repository.PublishRepository;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:57
 * @desc
 */
@Factory
public class PublishFactory {

    @Resource
    private PublishRepository publishRepository;


    public PublishE createPublishEntity() {
        PublishE publishE = new PublishE();
        publishE.setPublishRepository(publishRepository);
        return publishE;
    }

    public PublishE findPublishE(Long id) {
        return publishRepository.findPublishE(id);
    }

}
