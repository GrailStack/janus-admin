package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.ports.PublishIpPort;
import org.xujin.janus.infrastructure.converter.PublishIpConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishIpMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishIpDO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/27 14:42
 * @desc
 */
@Adapter
public class PublishIpAdapter implements PublishIpPort {
    @Resource
    private PublishIpMapper mapper;
    @Resource
    private PublishIpConverter converter;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(PublishIpE publishIpE) {
        List<PublishIpDO> list = converter.entityToData(publishIpE);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(x -> {
                mapper.insert(x);
            });
        }
    }

    @Override
    public PublishIpE findPublishIpE(Long id) {
        return converter.dataToEntity(mapper.findById(id));
    }

    @Override
    public int updateStatus(Long id, ReleaseStatusEnum status) {
        return mapper.updateStatus(id, status.getCode());
    }

    @Override
    public List<PublishIpE> findPublishIpEListByPublishId(Long publishId, ReleaseTypeEnum type) {
        List<PublishIpDO> doList = mapper.findByPublishIdAndType(publishId, type.getCode());
        return converter.dataToEntities(doList);
    }

    @Override
    public int batchUpdatePublishIpStatus(List<Long> publishIpIdList, ReleaseStatusEnum status) {
        UpdateWrapper<PublishIpDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status.getCode());
        updateWrapper.in("id", publishIpIdList);
        updateWrapper.eq("is_deleted", "0");
        return mapper.update(null, updateWrapper);
    }

}
