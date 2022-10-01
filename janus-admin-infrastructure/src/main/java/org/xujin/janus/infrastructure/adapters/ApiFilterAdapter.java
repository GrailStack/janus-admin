package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.ApiFilterE;
import org.xujin.janus.domain.user.ports.ApiFilterPort;
import org.xujin.janus.infrastructure.converter.ApiFilterConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiFilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiFilterDO;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/6/17 13:29
 * @desc
 */
@Adapter
public class ApiFilterAdapter implements ApiFilterPort {

    @Resource
    private ApiFilterMapper apiFilterMapper;

    @Resource
    private ApiFilterConverter apiFilterConverter;

    @Override
    public void save(ApiFilterE apiFliterE) {
        ApiFilterDO apiFilterDO = apiFilterConverter.entityToData(apiFliterE);
        apiFilterDO.setId(null);
        apiFilterMapper.insert(apiFilterDO);
    }

    @Override
    public void update(ApiFilterE apiFliterE) {
        ApiFilterDO apiFilterDO = apiFilterConverter.entityToData(apiFliterE);
        if (apiFilterDO.getId() != null) {
            apiFilterMapper.updateById(apiFilterDO);
        } else {
            apiFilterMapper.insert(apiFilterDO);
        }
    }
}
