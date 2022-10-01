package org.xujin.janus.cluster.test.callServer;

import org.xujin.janus.app.server.client.ServerRequests;
import org.xujin.janus.client.cmo.ChangeOnlineCmd;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.junit.Assert;
import org.junit.Test;

public class SendOnlineCmdTest {

    private static final String address = "127.0.0.1:8082";
    private static final String cluster = "default";
    private static final String version = "0.0.1-SNAPSHOT";

    @Test
    public void testChangeOnline() throws Exception {
        ChangeOnlineCmd cmd = new ChangeOnlineCmd();
        cmd.setOnline(false);
        JanusCmdMsg janusCmdMsg = ServerRequests.sendOnlineChanged(address, cluster, version,cmd);
        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }
}
