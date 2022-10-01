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
public class SendRoutePredicateChangedTest {
    private static final String address = "127.0.0.1:8082";
    private static final String cluster = "default";
    private static final String version = "0.0.1";

    @Test
    public void testUpdatePredicates() throws Exception {
        RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
        RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
        routeChangeDTO.setOperation(OperationEnum.UPDATE);
        routeChangeDTO.setDataJson(getUpdatePredicate());
        routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.BASE_CONFIG);
        routeChangeCmd.setRouteChangeDTOS(Arrays.asList(routeChangeDTO));
         ServerRequests.sendRouteChanged(address, cluster, version,"1", routeChangeCmd);
//        System.out.println("ResponseMessage:" + janusCmdMsg.getMessage());
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }

    private String getAddPredicate() {
        return "{\n" +
                "\t\t\t\"id\": \"singleFlowAdmin\",\n" +
                "\t\t\t\"predicates\": [{\n" +
                "\t\t\t\t\"name\": \"PathPrecise\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"paths\": \"/singleFlowAdmin/api/flow/tenant/detail\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"uri\": \"http://10.28.151.109:8080/api/flow/tenant/detail?id\\u003d10281\",\n" +
                "\t\t\t\"metadata\": {},\n" +
                "\t\t\t\"order\": 0\n" +
                "\t\t}";
    }

    private String getUpdatePredicate() {
        return "{\n" +
                "\t\t\t\"id\": \"singleFlowAdminNew\",\n" +
                "\t\t\t\"predicates\": [{\n" +
                "\t\t\t\t\"name\": \"PathPrecise\",\n" +
                "\t\t\t\t\"args\": {\n" +
                "\t\t\t\t\t\"paths\": \"/singleFlowAdmin/api/flow/tenant/detail\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"uri\": \"http://10.28.151.109:8080/api/flow/tenant/detail?id\\u003d10281\",\n" +
                "\t\t\t\"metadata\": {},\n" +
                "\t\t\t\"order\": 0\n" +
                "\t\t}";
    }


    private String getDeletePredicate() {
        return "{\n" +
                "\t\t\t\"id\": \"singleFlowAdminNew\",\n" +
                "\t\t\t\"predicates\": [{\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"uri\": \"http://10.28.151.109:8080/api/flow/tenant/detail?id\\u003d10281\",\n" +
                "\t\t\t\"metadata\": {},\n" +
                "\t\t\t\"order\": 0\n" +
                "\t\t}";
    }
}
