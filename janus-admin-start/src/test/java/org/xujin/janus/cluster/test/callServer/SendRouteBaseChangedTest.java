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
 * @author: ganshitao
 * @date: 2020/5/26
 */
public class SendRouteBaseChangedTest {
    private static final String address = "127.0.0.1:8082";
    private static final String cluster = "default";
    private static final String version = "0.0.1";


    @Test
    public void testAdd() throws Exception {
        RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
        RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
        routeChangeDTO.setOperation(OperationEnum.ADD);
        routeChangeDTO.setDataJson(getNewRoute());
        routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.BASE_CONFIG);
        routeChangeCmd.setRouteChangeDTOS(Arrays.asList(routeChangeDTO));
         ServerRequests.sendRouteChanged(address, cluster, version,"1", routeChangeCmd);
//        System.out.println("ResponseMessage:" + janusCmdMsg.getMessage());
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }

    @Test
    public void testUpdate() throws Exception {
        RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
        RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
        routeChangeDTO.setOperation(OperationEnum.UPDATE);
        routeChangeDTO.setDataJson(getUpdateRoute());
        routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.BASE_CONFIG);
        routeChangeCmd.setRouteChangeDTOS(Arrays.asList(routeChangeDTO));
        ServerRequests.sendRouteChanged(address, cluster, version,"1", routeChangeCmd);
//        System.out.println("ResponseMessage:" + janusCmdMsg.getMessage());
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }

    @Test
    public void testDelete() throws Exception {
        RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
        RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
        routeChangeDTO.setOperation(OperationEnum.DELETE);
        routeChangeDTO.setDataJson(getDeleteRoute());
        routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.BASE_CONFIG);
        routeChangeCmd.setRouteChangeDTOS(Arrays.asList(routeChangeDTO));
         ServerRequests.sendRouteChanged(address, cluster, version, "1",routeChangeCmd);
//        System.out.println("ResponseMessage:" + janusCmdMsg.getMessage());
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }

    private String getNewRoute() {
        return "{\n" +
                "\t\t\t\"id\": \"singleFlowAdminNew\",\n" +
                "\t\t\t\"predicates\": [{\n" +
                "\t\t\t\t\"name\": \"PathRegex\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"pattern\": \"^/singleFlowAdminNew/.*\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"filters\": [{\n" +
                "\t\t\t\t\"name\": \"StripPrefix\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"part\": \"1\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"name\": \"Print\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"X-Request-Blue\": \"Blue\",\n" +
                "\t\t\t\t\t\"X-Request-Red\": \"Red\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"name\": \"AddResponseHeader\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"Access-Control-Allow-Origin\": \"*\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"uri\": \"http://10.28.151.109:8080/api/flow/tenant/detail?id\\u003d10281\",\n" +
                "\t\t\t\"metadata\": {},\n" +
                "\t\t\t\"order\": 0\n" +
                "\t\t}";
    }

    private String getUpdateRoute() {
        return "{\n" +
                "\t\t\t\"id\": \"singleFlowAdminNew\",\n" +
                "\t\t\t\"predicates\": [{\n" +
                "\t\t\t\t\"name\": \"PathRegex\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"pattern\": \"^/singleFlowAdminUpdate/.*\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"filters\": [{\n" +
                "\t\t\t\t\"name\": \"StripPrefix\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"part\": \"1\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"name\": \"AddRequestHeader\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"X-Request-Blue\": \"Blue\",\n" +
                "\t\t\t\t\t\"X-Request-Red\": \"Red\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"name\": \"AddResponseHeader\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"Access-Control-Allow-Origin\": \"*\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"uri\": \"http://10.28.151.109:8080/api/flow/tenant/detail?id\\u003d10281\",\n" +
                "\t\t\t\"metadata\": {},\n" +
                "\t\t\t\"order\": 0\n" +
                "\t\t}";
    }

    private String getDeleteRoute() {
        return "{\n" +
                "\t\t\t\"id\": \"singleFlowAdminNew\"\n" +
                "\t\t}";
    }
}
