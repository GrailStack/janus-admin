package org.xujin.janus.controller;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.QueryRoleListCmdExe;
import org.xujin.janus.app.command.cmo.AddOrUpdateRoleCmd;
import org.xujin.janus.app.command.cmo.DeleteRoleByIdCmd;
import org.xujin.janus.app.command.cmo.query.PageQueryRoleCmd;
import org.xujin.janus.app.command.cmo.query.QueryPerminByRoleCmd;
import org.xujin.janus.app.command.co.QueryPerminByRoleCO;
import org.xujin.janus.app.command.co.RoleCO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
@Api("角色管理")
public class RoleController {


    @Autowired
    private CommandBus commandBus;


    @PostMapping("/page")
    @ApiOperation(value = "分页查询角色")
    @RequiresPermissions("role:query")
    public ResultData<PageResult<RoleCO>> getRolePage(@RequestBody PageQueryRoleCmd pageQueryRoleCmd) {
        return commandBus.send(pageQueryRoleCmd);
    }

    @GetMapping("/list")
    @ApiOperation(value = "角色列表")
    @RequiresPermissions("role:list")
    public ResultData<PageResult<RoleCO>> getRoleList() {
        return commandBus.send(QueryRoleListCmdExe.class);
    }


    @GetMapping("/queryPermByRole")
    @ApiOperation(value = "根据Role查询权限")
    @RequiresPermissions("role:permin:query")
    public ResultData<List<QueryPerminByRoleCO>> queryPerminByRole(@RequestParam(name = "roleName",
            required=false) String roleName) {
        QueryPerminByRoleCmd perminByRoleCmd=new QueryPerminByRoleCmd();
        perminByRoleCmd.setRoleName(roleName);
        return commandBus.send(perminByRoleCmd);
    }


    @PostMapping("/addOrUpdate")
    @ApiOperation(value = "增加或更新角色")
    @RequiresPermissions("role:add")
    public ResultData<Void> addOrUpdateRole(@RequestBody AddOrUpdateRoleCmd addOrUpdateRoleCmd) {
        return commandBus.send(addOrUpdateRoleCmd);
    }


    @GetMapping("/delete")
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("role:delete")
    public ResultData<Void> delete(Long id) {
        DeleteRoleByIdCmd deleteRoleByIdCmd= new DeleteRoleByIdCmd();
        deleteRoleByIdCmd.setId(id);
        return commandBus.send(deleteRoleByIdCmd);
    }



}
