package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.co.UserMenuCO;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.MenuController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/17 13:40
 * @desc
 */
@Slf4j
public class MenuControllerTest extends CommonTest {

    @Resource
    private MenuController menuController;

    @Test
    public void testSave() {
        ResultData<List<UserMenuCO>> allMenu = menuController.getAllMenu();
        System.out.println(allMenu);
    }

}
