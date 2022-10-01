package org.xujin.janus.infrastructure.security;

import com.google.common.collect.Lists;
import org.xujin.halo.shiro.api.ShiroAccountProvider;
import org.xujin.halo.shiro.model.Account;
import org.xujin.janus.infrastructure.security.user.UserRoleEnum;
import org.xujin.janus.infrastructure.tunnel.db.dao.*;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PermissionDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RolePermissionDO;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.xujin.janus.infrastructure.tunnel.db.dao.*;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserDO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/14 16:16
 * @desc
 */
@Service
public class AccountProviderImpl implements ShiroAccountProvider {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Account loadAccount(String account) throws AuthenticationException {
        UserDO userDO=userMapper.findUserByUserName(account);
        if(null == userDO){
            throw new AuthenticationException("账号或密码错误");
        }
        return userDO;
    }

    @Override
    public Set<String> loadRoles(String account) {
        List<String> userRoles = userRolesMapper.findUserRoles(account);
        if (CollectionUtils.isEmpty(userRoles)) {
            userRoles = Lists.newArrayList(UserRoleEnum.USER.getRole());
        }
        Set<String> validRoles = roleMapper.findValidRoles(userRoles)
                .stream()
                .map(RoleDO::getKey)
                .collect(Collectors.toSet());

        if (inBlackList(validRoles)) {
            return null;
        }
        return new HashSet(validRoles);
    }

    protected boolean inBlackList(Set<String> roles) {
        return roles.contains(UserRoleEnum.BLACK.getRole());
    }

    @Override
    public Set<String> loadPermissions(String account) {
        return null;
    }

    @Override
    public Set<String> loadPermissions(Set<String> validRoles) {
        Set<Long> permissionIds = rolePermissionMapper.findPermissionByRoles(validRoles)
                .stream().map(RolePermissionDO::getPermissionId).collect(Collectors.toSet());

        Set<String> permissions = permissionMapper.findValidPermissions(permissionIds)
                .stream().map(PermissionDO::getPermission).collect(Collectors.toSet());
        return permissions;
    }
}
