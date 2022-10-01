package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.UserE;
import org.xujin.janus.domain.user.ports.UserPort;
import org.xujin.janus.infrastructure.converter.UserConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.UserMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author halo codegen
 * for demo
 **/
@Adapter
public class UserAdapter implements UserPort {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;

    @Override
    public void save(UserE useE) {
        UserDO userDO = userConverter.entityToData(useE);
        userMapper.insert(userDO);
    }

    @Override
    public void update(UserE useE) {
        UserDO userDO = userConverter.entityToData(useE);
        userMapper.updateById(userDO);
    }

    @Override
    public Map<String, String> getNames(List<String> userNames) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("username", userNames);
        return userMapper
                .selectList(queryWrapper)
                .stream()
                .collect(Collectors.toMap(UserDO::getUserName, UserDO::getName, (name1, name2) -> name1));
    }

}