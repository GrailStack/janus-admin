package org.xujin.janus.controller;

import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.UpSertMenuCmd;
import org.xujin.janus.app.command.cmo.DeleteMenuCmd;
import org.xujin.janus.app.command.cmo.query.QuerySubMenuCmdByPidCmd;
import org.xujin.janus.app.command.co.UserMenuCO;
import org.xujin.janus.app.command.cmo.query.QueryUserMenuCmd;
import org.xujin.janus.app.command.query.QueryAllMenuCmdExe;
import org.xujin.janus.app.converter.MenuClientConverter;
import org.xujin.janus.infrastructure.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yage.luan
 * created at 2019/5/11 17:30
 **/
@RestController
@RequestMapping("/admin/menu")
@Api("菜单管理")
public class MenuController {

    @Autowired
    protected CommandBus commandBus;

    @Autowired
    protected MenuClientConverter menuClientConverter;

    @GetMapping("/queryMenu")
    @ApiOperation(value = "查询登录用户菜单")
    @RequiresAuthentication
    public ResultData<List<UserMenuCO>> getUserMenu() {
        QueryUserMenuCmd cmd = new QueryUserMenuCmd().setUserName(UserUtil.getCurrUser());
        return commandBus.send(cmd);
    }

    @PostMapping("/manage/upsert")
    @ApiOperation(value = "创建或更新菜单")
    @RequiresPermissions("menu:modify")
    public ResultData<Long> upsertMenu(@RequestBody UpSertMenuCmd cmd) {
        return commandBus.send(cmd);
    }

    @PostMapping("/manage/delete")
    @ApiOperation(value = "删除菜单")
    @RequiresPermissions("menu:delete")
    public ResultData deleteMenu(@RequestBody DeleteMenuCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/manage/allMenu")
    @ApiOperation(value = "查询所有菜单")
    @RequiresPermissions("menu:query:all")
    public ResultData<List<UserMenuCO>> getAllMenu() {
        return commandBus.send(QueryAllMenuCmdExe.class);
    }

    @GetMapping("/manage/querySubMenuByParentId")
    @ApiOperation(value = "根据parentId查询子菜单")
    @RequiresPermissions("menu:query:sub")
    public ResultData<List<UserMenuCO>> querySubMenuByParentId(Long parentId) {
        return commandBus.send(new QuerySubMenuCmdByPidCmd().setParentId(parentId));
    }


}
