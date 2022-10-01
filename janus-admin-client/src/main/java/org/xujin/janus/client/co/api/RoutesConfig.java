package org.xujin.janus.client.co.api;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: gan
 * @date: 2020/4/17
 */
public class RoutesConfig {

    private List<ApiCO> routes = new CopyOnWriteArrayList<>();

    public void setRoutes(List<ApiCO> routes) {
        this.routes = routes;
    }

    public List<ApiCO> getRoutes() {
        return routes;
    }


}
