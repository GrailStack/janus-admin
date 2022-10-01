package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.UserE;
import org.xujin.janus.domain.user.entity.UserRolesE;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserRolesDO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/16 11:39
 * @desc
 */
@Component
public class UserRolesConverter implements ConverterI {

    public UserRolesE dataToEntity(UserRolesDO userRolesDO) {
        UserRolesE userRolesE = BeanMapper.map(userRolesDO, UserRolesE.class);
        return userRolesE;
    }

    public List<UserRolesE> dataToEntities(List<UserRolesDO> list) {
        return Optional.ofNullable(list).orElse(Arrays.asList())
                .stream()
                .map(this::dataToEntity)
                .collect(Collectors.toList());
    }

}
