package org.xujin.janus.client.co.api;

import org.xujin.janus.client.co.api.netty.NettyClientConfig;
import org.xujin.janus.client.co.api.netty.NettyServerConfig;

import java.io.Serializable;
import java.util.List;

public class ServerConfig implements Serializable {

    private NettyServerConfig serverConf;
    private NettyClientConfig clientConf;

    private RoutesConfig routesConfig;


    private List<String>  globalFilters;
    private DynamicClassConfig dynamicClass;
    private JanusInfluxConfig janusInfluxConfig;

    public NettyServerConfig getServerConf() {
        return serverConf;
    }

    public void setServerConf(NettyServerConfig serverConf) {
        this.serverConf = serverConf;
    }

    public NettyClientConfig getClientConf() {
        return clientConf;
    }

    public void setClientConf(NettyClientConfig clientConf) {
        this.clientConf = clientConf;
    }



    public List<String> getGlobalFilters() {
        return globalFilters;
    }

    public void setGlobalFilters(List<String> globalFilters) {
        this.globalFilters = globalFilters;
    }

    public DynamicClassConfig getDynamicClass() {
        return dynamicClass;
    }

    public void setDynamicClass(DynamicClassConfig dynamicClass) {
        this.dynamicClass = dynamicClass;
    }

    public JanusInfluxConfig getJanusInfluxConfig() {
        return janusInfluxConfig;
    }

    public void setJanusInfluxConfig(JanusInfluxConfig janusInfluxConfig) {
        this.janusInfluxConfig = janusInfluxConfig;
    }

    public RoutesConfig getRoutesConfig() {
        return routesConfig;
    }

    public void setRoutesConfig(RoutesConfig routesConfig) {
        this.routesConfig = routesConfig;
    }
}
