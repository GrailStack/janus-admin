package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.UserE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/4/15 16:55
 * @desc
 */
@Component
public class UserConverter implements ConverterI {

    public UserDO entityToData(UserE userE) {
        UserDO useDO = BeanMapper.map(userE, UserDO.class);
        return useDO;
    }

    public UserE dataToEntity(UserDO userDO) {
        UserE userE = BeanMapper.map(userDO, UserE.class);
        return userE;
    }

}
