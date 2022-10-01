package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.halo.event.EventBus;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.UserRolesE;
import org.xujin.janus.domain.user.event.CreateUserRoleEvent;
import org.xujin.janus.domain.user.ports.UserRolePort;
import org.xujin.janus.domain.user.valueobject.UserRolesVO;
import org.xujin.janus.infrastructure.converter.UserRolesConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.UserRolesMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserRolesDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/15 17:24
 * @desc
 */
@Adapter
public class UserRolesAdapters implements UserRolePort {


    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private UserRolesConverter userRolesConverter;

    @Autowired
    private EventBus eventBus;


    @Override
    public Long createUserRole(String userName, String role) {
        QueryWrapper<UserRolesDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        queryWrapper.eq("role", role);
        UserRolesDO userRolesDO = userRolesMapper.selectOne(queryWrapper);
        if (userRolesDO != null) {
            userRolesDO.setIsDeleted((byte) 0);
            userRolesMapper.updateById(userRolesDO);
        } else {
            userRolesDO = new UserRolesDO();
            userRolesDO.setUsername(userName).setRole(role).setGmtCreate(new Timestamp(System.currentTimeMillis()));
            userRolesMapper.insert(userRolesDO);
        }
        return userRolesDO.getId();
    }

    @Override
    public void save(UserRolesVO userRolesVO) {
        userRolesMapper.insert(BeanMapper.map(userRolesVO, UserRolesDO.class));
    }

    @Override
    public void update(UserRolesVO userRolesVO) {
        UserRolesDO userRolesDO=userRolesMapper.queryByUserName(userRolesVO.getUsername());
        userRolesDO.setRole(userRolesVO.getRole());
        userRolesMapper.updateById((userRolesDO));
    }

    @Override
    public List<UserRolesE> queryByRole(String role) {
        List<UserRolesDO> userRolesDOS = userRolesMapper.queryByRole(role);
        List<UserRolesE> userRolesE = userRolesConverter.dataToEntities(userRolesDOS);
        return userRolesE;
    }

    @Override
    public boolean isAdmin(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return false;
        }
        List<String> userRoles = userRolesMapper.findUserRoles(userName);
        if (CollectionUtils.isEmpty(userRoles)) {
            CreateUserRoleEvent event = new CreateUserRoleEvent();
            event.setRole("USER");
            event.setUserName(userName);
            eventBus.asyncPublishEvent(event);
            return false;
        }
        return userRoles.contains("ADMIN");
    }
}
