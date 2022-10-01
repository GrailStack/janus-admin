package org.xujin.janus.app.server.processer;

import com.alibaba.fastjson.JSON;
import org.xujin.halo.annotation.app.AppService;
import org.xujin.janus.app.server.processer.step.QueryApiStep;
import org.xujin.janus.app.server.processer.step.QueryStartUpStep;
import org.xujin.janus.client.cmo.QueryAllConfigCmd;
import org.xujin.janus.client.co.AllConfigCO;
import org.xujin.janus.client.co.api.ApiCO;
import org.xujin.janus.client.co.api.RoutesConfig;
import org.xujin.janus.client.co.api.ServerConfig;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.xujin.janus.domain.user.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关Server启动的时候查询 所有的配置
 * @author xujin
 */
@AppService
public class QueryAllConfigProcessor extends AbstractProcessor {

    @Autowired
    private QueryStartUpStep queryStartUpStep;

    @Autowired
    private FilterService filterService;

    @Autowired
    private QueryApiStep queryApiStep;

    @Override
    public JanusCmdMsg doExecute(JanusCmdMsg msg) {
        ServerConfig serverConfig=new ServerConfig();
        QueryAllConfigCmd queryAllConfigCmd = (QueryAllConfigCmd) msg.getPayload();
        AllConfigCO allConfigCO = new AllConfigCO();
        //查询集群配置
        serverConfig = queryStartUpStep.execute(msg.getCluster());
        /**
        List<String> filterCodeList = filterService.globalFilters(msg.getCluster());
        if (!CollectionUtils.isEmpty(filterCodeList)) {
            map.put("globalFilters", filterCodeList.toArray(new String[0]));
        }**/
        List<String> filterCodeList=new ArrayList<>();
        filterCodeList.add("CircuitBreaker");
        filterCodeList.add("Retry");
        serverConfig.setGlobalFilters(filterCodeList);
        List<ApiCO> apiCOS=queryApiStep.execute(msg.getCluster());
        RoutesConfig routesConfig=new RoutesConfig();
        routesConfig.setRoutes(apiCOS);
        serverConfig.setRoutesConfig(routesConfig);
        allConfigCO.setConfigJson(JSON.toJSONString(serverConfig));
        return successResponse(msg, allConfigCO);
    }

}
