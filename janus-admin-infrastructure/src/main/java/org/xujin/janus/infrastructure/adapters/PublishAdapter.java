package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.ports.PublishPort;
import org.xujin.janus.infrastructure.converter.PublishConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishDO;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/27 14:30
 * @desc
 */
@Adapter
public class PublishAdapter implements PublishPort {
    @Resource
    private PublishMapper mapper;
    @Resource
    private PublishConverter publishConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(PublishE publishE) {
        PublishDO publishDO = publishConverter.entityToData(publishE);
        mapper.insert(publishDO);
        publishE.setId(publishDO.getId());
    }

    @Override
    public PublishE findPublishE(Long id) {
        return publishConverter.dataToEntity(mapper.findById(id));
    }

    @Override
    public int updateStatus(Long id, ReleaseStatusEnum status) {
        return mapper.updateStatus(id, status.getCode());
    }

}
