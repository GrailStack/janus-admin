package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.event.EventBus;
import org.xujin.janus.app.command.cmo.query.QueryUserMenuCmd;
import org.xujin.janus.app.command.co.UserMenuCO;
import org.xujin.janus.app.converter.MenuClientConverter;
import org.xujin.janus.domain.user.event.CreateUserRoleEvent;
import org.xujin.janus.infrastructure.tunnel.db.dao.MenuMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.UserRolesMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.MenuDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yage.luan
 * created at 2019/5/11 17:38
 **/
@Slf4j
@CmdHandler
public class QueryUserMenuCmdExe implements CommandExecutorI<ResultData<List<UserMenuCO>>, QueryUserMenuCmd> {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private MenuClientConverter menuClientConverter;

    @Autowired
    private EventBus eventBus;

    @Override
    public ResultData<List<UserMenuCO>> execute(QueryUserMenuCmd cmd) {
        ResultData<List<UserMenuCO>> resultData = ResultData.success(null);
        if (!check(cmd, resultData)) {
            return resultData;
        }
        List<String> userRoles = getUserRoles(cmd.getUserName());
        List<MenuDO> rolesMenu = getRolesMenu(userRoles);
        resultData.setData(menuClientConverter.dataToClient(rolesMenu));
        return resultData;
    }

    protected List<String> getUserRoles(String userName) {
        List<String> userRoles = userRolesMapper.findUserRoles(userName);
        if (CollectionUtils.isEmpty(userRoles)) {
            CreateUserRoleEvent event = new CreateUserRoleEvent();
            event.setRole("USER");
            event.setUserName(userName);
            eventBus.asyncPublishEvent(event);
            return Lists.newArrayList("USER");
        }
        return userRoles;
    }

    protected List<MenuDO> getRolesMenu(List<String> roleList) {
        if (CollectionUtils.isEmpty(roleList)) {
            return Lists.newArrayList();
        }
        QueryWrapper<MenuDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        List<MenuDO> allMenu = menuMapper.selectList(queryWrapper);
        return allMenu.stream().filter(menu -> {
            String roles = menu.getRoles();
            if (org.springframework.util.StringUtils.isEmpty(roles)) {
                return true;
            } else {
                HashSet<String> roleSet = new HashSet(Arrays.asList(roles.split(",")));
                return roleList.stream().filter(roleSet::contains).findAny().isPresent();
            }
        }).collect(Collectors.toList());
    }


    protected boolean check(QueryUserMenuCmd cmd, ResultData<List<UserMenuCO>> resultData) {
        try {
            Preconditions.checkNotNull(cmd, "cmd不能为null");
            Preconditions.checkArgument(StringUtils.isNotEmpty(cmd.getUserName()), "userName不能为空");
        } catch (Exception ex) {
            resultData.setMsgContent(ex.getMessage());
            return false;
        }
        return true;
    }

}
