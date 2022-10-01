package org.xujin.janus.app.command.query;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorWithoutInputI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.co.UserMenuCO;
import org.xujin.janus.app.converter.MenuClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.MenuMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.MenuDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 17:17
 **/
@Slf4j
@CmdHandler
public class QueryAllMenuCmdExe implements CommandExecutorWithoutInputI<ResultData<List<UserMenuCO>>> {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuClientConverter menuClientConverter;

    @Override
    public ResultData<List<UserMenuCO>> execute() {
        List<MenuDO> allMenu = menuMapper.getAllMenu();
        List<UserMenuCO> allMenus = menuClientConverter.dataToClient(allMenu);
        return ResultData.success(allMenus);
    }

}
