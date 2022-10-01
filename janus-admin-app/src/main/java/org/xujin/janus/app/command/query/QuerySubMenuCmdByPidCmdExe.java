package org.xujin.janus.app.command.query;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.query.QuerySubMenuCmdByPidCmd;
import org.xujin.janus.app.command.co.UserMenuCO;
import org.xujin.janus.infrastructure.converter.MenuConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.MenuMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.MenuDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据ParentId查询子菜单列表
 *
 * @author xujin
 */
@CmdHandler
public class QuerySubMenuCmdByPidCmdExe implements CommandExecutorI<ResultData<List<UserMenuCO>>, QuerySubMenuCmdByPidCmd> {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuConverter menuConverter;

    @Override
    public ResultData<List<UserMenuCO>> execute(QuerySubMenuCmdByPidCmd cmd) {
        List<MenuDO> menuDOs = menuMapper.findMenusByParentId(cmd.getParentId());
        List<UserMenuCO> allMenus=new ArrayList<>();
        for (MenuDO menu:menuDOs) {
            UserMenuCO userMenuCO= BeanMapper.map(menu,UserMenuCO.class);
            userMenuCO.setRoleList(menuConverter.split(menu.getRoles()));
            allMenus.add(userMenuCO);
        }
        return ResultData.success(allMenus);
    }


}

