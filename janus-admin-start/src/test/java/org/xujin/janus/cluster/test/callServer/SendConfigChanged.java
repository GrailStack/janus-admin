package org.xujin.janus.cluster.test.callServer;

import org.xujin.janus.app.server.client.ServerRequests;
import org.xujin.janus.client.cmo.ConfigChangeCmd;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ganshitao
 * @date: 2020/5/27
 */
public class SendConfigChanged {
    private static final String address = "127.0.0.1:8082";
    private static final String cluster = "default";
    private static final String version = "0.0.1";

    @Test
    public void testGlobalFilterChange() throws Exception {
        Map<String, String> changedItem = new HashMap<>(1);
        changedItem.putIfAbsent("globalFilters", "AuthG,Print");
        ConfigChangeCmd configChangeCmd = new ConfigChangeCmd(changedItem);
         ServerRequests.sendConfigChanged(address, cluster, version,"1", configChangeCmd);
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }
}
