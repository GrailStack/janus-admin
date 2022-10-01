package org.xujin.janus.cluster.test.callServer;

import org.xujin.janus.app.server.client.ServerRequests;
import org.xujin.janus.client.cmo.OperationEnum;
import org.xujin.janus.client.cmo.RouteChangeCmd;
import org.xujin.janus.client.cmo.RouteChangeDTO;
import org.xujin.janus.client.cmo.RouteChangeTypeEnum;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: gan
 * @date: 2020/6/1
 */
public class SendRouteFilterChangedTest {
    private static final String address = "127.0.0.1:8082";
    private static final String cluster = "default";
    private static final String version = "0.0.1";

    @Test
    public void testAdd() throws Exception {
        RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
        RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
        routeChangeDTO.setRouteId("singleFlowAdmin");
        routeChangeDTO.setOperation(OperationEnum.ADD);
        routeChangeDTO.setDataJson(getFilterConfigNew());
        routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.FILTER_CONFIG);
        routeChangeCmd.setRouteChangeDTOS(Arrays.asList(routeChangeDTO));
        ServerRequests.sendRouteChanged(address, cluster, version,"1", routeChangeCmd);
//        System.out.println("ResponseMessage:" + janusCmdMsg.getMessage());
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }
    @Test
    public void testUpdate() throws Exception {
        RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
        RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
        routeChangeDTO.setRouteId("singleFlowAdmin");
        routeChangeDTO.setOperation(OperationEnum.UPDATE);
        routeChangeDTO.setDataJson(getFilterConfigUpdate());
        routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.FILTER_CONFIG);
        routeChangeCmd.setRouteChangeDTOS(Arrays.asList(routeChangeDTO));
         ServerRequests.sendRouteChanged(address, cluster, version,"1", routeChangeCmd);
//        System.out.println("ResponseMessage:" + janusCmdMsg.getMessage());
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }
    @Test
    public void testDelete() throws Exception {
        RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
        RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
        routeChangeDTO.setRouteId("singleFlowAdmin");
        routeChangeDTO.setOperation(OperationEnum.DELETE);
        routeChangeDTO.setDataJson(getFilterConfigUpdate());
        routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.FILTER_CONFIG);
        routeChangeCmd.setRouteChangeDTOS(Arrays.asList(routeChangeDTO));
         ServerRequests.sendRouteChanged(address, cluster, version,"1", routeChangeCmd);
//        System.out.println("ResponseMessage:" + janusCmdMsg.getMessage());
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }
    @Test
    public void testAdd_exist() throws Exception {
        RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
        RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
        routeChangeDTO.setRouteId("singleFlowAdmin");
        routeChangeDTO.setOperation(OperationEnum.ADD);
        routeChangeDTO.setDataJson(getFilterConfigNew_exist());
        routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.FILTER_CONFIG);
        routeChangeCmd.setRouteChangeDTOS(Arrays.asList(routeChangeDTO));
        ServerRequests.sendRouteChanged(address, cluster, version,"1", routeChangeCmd);
//        System.out.println("ResponseMessage:" + janusCmdMsg.getMessage());
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }
    private String getFilterConfigNew() {
        return "{\"name\":\"PrefixPath\",\"args\":{\"config2\":\"auto2\",\"config1\":\"auto1\"}}";
    }
    private String getFilterConfigNew_exist() {
        return "{\"name\":\"AddRequestHeader\",\"args\":{\"config2\":\"auto2\",\"config1\":\"auto1\"}}";
    }
    private String getFilterConfigUpdate() {
        return "{\"name\":\"AddResponseHeader\",\"args\":{\"config2\":\"auto2\",\"config1\":\"auto1\"}}";
    }
}
