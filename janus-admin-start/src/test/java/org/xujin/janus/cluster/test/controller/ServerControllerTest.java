package org.xujin.janus.cluster.test.controller;

import org.xujin.janus.app.command.cmo.ChangeOnlineStateCmd;
import org.xujin.janus.app.server.client.ServerRequests;
import org.xujin.janus.client.cmo.ChangeOnlineCmd;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.ServerController;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/6/12 13:42
 * @desc
 */
public class ServerControllerTest extends CommonTest {

    @Resource
    private ServerController serverController;

    @Test
    public void testSendOnlineChanged() throws Exception {
        ChangeOnlineStateCmd changeOnlineStateCmd = new ChangeOnlineStateCmd();
        changeOnlineStateCmd.setClusterCode("test");
        changeOnlineStateCmd.setOnline(false);
        changeOnlineStateCmd.setPort(8082);
        changeOnlineStateCmd.setIp("192.168.46.108");
        serverController.changeOnline(changeOnlineStateCmd);
    }

}
