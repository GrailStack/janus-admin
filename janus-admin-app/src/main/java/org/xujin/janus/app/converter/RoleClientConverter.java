package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.co.RoleCO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yage.luan
 * created at 2019/5/22 20:47
 **/
@Component
public class RoleClientConverter implements ConverterI {

    public RoleCO dataToClient(RoleDO roleDO) {
        if (roleDO == null) {
            return null;
        }
        return BeanMapper.map(roleDO, RoleCO.class);
    }

    public List<RoleCO> dataToClient(List<RoleDO> roleDOList) {
        if (CollectionUtils.isEmpty(roleDOList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(roleDOList, RoleDO.class, RoleCO.class);
    }

}
