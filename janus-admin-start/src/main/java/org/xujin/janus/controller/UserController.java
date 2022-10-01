package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.app.command.cmo.query.PageQueryClusterUserCmd;
import org.xujin.janus.app.command.cmo.query.PageQueryUserCmd;
import org.xujin.janus.app.command.cmo.query.QueryClusterUserDetailCmd;
import org.xujin.janus.app.command.cmo.query.QueryUserLoginDetailCmd;
import org.xujin.janus.app.command.co.RoleCO;
import org.xujin.janus.app.command.co.UserCO;
import org.xujin.janus.app.command.co.UserLoginInfo;
import org.xujin.janus.app.command.query.QueryAllRoleCmdExe;
import org.xujin.janus.infrastructure.ClusterUserDetailCo;
import org.xujin.janus.infrastructure.utils.SessionUtils;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 17:51
 **/
@RestController
@RequestMapping("/admin/user")
@Api("用户管理")
public class UserController {

    @Autowired
    protected CommandBus commandBus;

    @GetMapping("/manage/allRole")
    @ApiOperation(value = "查询所有角色")
    @RequiresAuthentication
    public ResultData<List<RoleCO>> getAllRole() {
        return commandBus.send(QueryAllRoleCmdExe.class);
    }


    @PostMapping("/page")
    @ApiOperation(value = "分页查询用户")
    @RequiresPermissions("user:query")
    public ResultData<PageResult<UserCO>> getUserPage(@RequestBody PageQueryUserCmd pageQueryUserCmd) {
        return commandBus.send(pageQueryUserCmd);
    }

    @PostMapping("/add")
    @ApiOperation(value = "增加用户")
    @RequiresPermissions("user:add")
    public ResultData<Void> addUser(@RequestBody AddUserCmd addUserCmd) {
        return commandBus.send(addUserCmd);
    }


    @PostMapping("/update")
    @ApiOperation(value = "更新用户")
    @RequiresPermissions("user:update")
    public ResultData<Void> updateUser(@RequestBody UpdateUserCmd updateUserCmd) {
        return commandBus.send(updateUserCmd);
    }


    @PostMapping("/addUserCluster")
    @ApiOperation(value = "新增用户与集群绑定")
    public ResultData<Void> addUserCluster(@RequestBody AddClusterUserCmd addClusterUserCmd) {
        return commandBus.send(addClusterUserCmd);
    }


    @PostMapping("/updateUserCluster")
    @ApiOperation(value = "更新用户与集群绑定")
    public ResultData<Void> updateUserCluster(@RequestBody UpdateClusterUserCmd updateClusterUserCmd) {
        return commandBus.send(updateClusterUserCmd);
    }


    @PostMapping("/deleteUserCluster")
    @ApiOperation(value = "删除用户与集群绑定")
    public ResultData<Void> deleteUserCluster(@RequestBody DeleteClusterUserCmd deleteClusterUserCmd) {
        return commandBus.send(deleteClusterUserCmd);
    }


    @GetMapping("/detail")
    @ApiOperation(value = "查询用户与集群绑定")
    public ResultData<ClusterUserDetailCo> detail(@ModelAttribute QueryClusterUserDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @PostMapping("/getClusterUserPage")
    @ApiOperation(value = "分页查询用户与集群绑定")
    public ResultData<PageResult<ClusterUserDetailCo>> getClusterUserPage(@RequestBody PageQueryClusterUserCmd pageQueryClusterUserCmd) {
        return commandBus.send(pageQueryClusterUserCmd);
    }

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "查询用户登录信息")
    public ResultData<UserLoginInfo> getUserInfo() {
        QueryUserLoginDetailCmd cmd = new QueryUserLoginDetailCmd();
        if (!StringUtils.isEmpty(SessionUtils.getUserName())) {
            cmd.setUserName(SessionUtils.getUserName());
        } else {
            throw new BusinessException("400", "请先登录");
        }
        return commandBus.send(cmd);
    }
}
