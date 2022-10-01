package org.xujin.janus.cluster.test.controller;

import org.xujin.janus.app.command.cmo.AddOrUpdateRoleCmd;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.RoleController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/16 11:46
 * @desc
 */
@Slf4j
public class RoleControllerTest extends CommonTest {

    @Resource
    private RoleController roleController;

    @Test
    public void testAdd() {
        AddOrUpdateRoleCmd cmd = new AddOrUpdateRoleCmd();
        cmd.setDesc("角色描述");
        cmd.setKey("role_key");
        cmd.setName("角色名");
        List<Long> objects = new ArrayList<>();
        objects.add(new Long("1"));
        cmd.setPIds(objects);
        roleController.addOrUpdateRole(cmd);
    }
}
