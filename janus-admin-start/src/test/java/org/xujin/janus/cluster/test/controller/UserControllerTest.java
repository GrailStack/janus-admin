package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddClusterUserCmd;
import org.xujin.janus.app.command.cmo.AddUserCmd;
import org.xujin.janus.app.command.cmo.query.PageQueryClusterUserCmd;
import org.xujin.janus.app.command.cmo.query.QueryClusterUserDetailCmd;
import org.xujin.janus.app.command.cmo.query.QueryUserLoginDetailCmd;
import org.xujin.janus.app.command.co.UserLoginInfo;
import org.xujin.janus.infrastructure.ClusterUserDetailCo;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author chenglong.ren
 * @date 2020/4/15 17:30
 * @desc
 */
@Slf4j
public class UserControllerTest extends CommonTest {

    @Resource
    private UserController userController;


    @Test
    public void testAdd() {
        AddUserCmd cmd = new AddUserCmd();
        cmd.setName("陈日建");
        cmd.setEmail("2323@qq.com");
        cmd.setPassword("admin");
        cmd.setUserName("rijian.chen");
        cmd.setRole("5");
        userController.addUser(cmd);
    }

    @Test
    public void testAddClusterUser() {
        AddClusterUserCmd cmd = new AddClusterUserCmd();
        cmd.setClusterCode("code");
        cmd.setUserName("chenglong");
        userController.addUserCluster(cmd);
    }

    @Test
    public void testDetail() {
        QueryClusterUserDetailCmd cmd = new QueryClusterUserDetailCmd();
        cmd.setId(new BigInteger("1"));
        ResultData<ClusterUserDetailCo> detail = userController.detail(cmd);
        System.out.println(detail);
    }

    @Test
    public void testPage() {
        PageQueryClusterUserCmd cmd = new PageQueryClusterUserCmd();
        ResultData<PageResult<ClusterUserDetailCo>> data = userController.getClusterUserPage(cmd);
        System.out.println(data);
    }


    @Test
    public void testAddClusterUserList() {
        AddClusterUserCmd cmd = new AddClusterUserCmd();
        cmd.setClusterCode("code");
        ArrayList<String> addList = new ArrayList<>();
        addList.add("zhang.san");
        addList.add("li.si");
        cmd.setAddList(addList);
        userController.addUserCluster(cmd);
    }

    @Test
    public void testUserLogin() {
        ResultData<UserLoginInfo> userInfo = userController.getUserInfo();
        System.out.println(userInfo);
    }
}
