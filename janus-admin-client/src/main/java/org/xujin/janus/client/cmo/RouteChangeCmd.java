package org.xujin.janus.client.cmo;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ganshitao
 * @date: 2020/5/22
 */
public class RouteChangeCmd implements Serializable {
    public static final String method = "routeChanged";
    private String requestID;
    private List<RouteChangeDTO> routeChangeDTOS;

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public List<RouteChangeDTO> getRouteChangeDTOS() {
        return routeChangeDTOS;
    }

    public void setRouteChangeDTOS(List<RouteChangeDTO> routeChangeDTOS) {
        this.routeChangeDTOS = routeChangeDTOS;
    }
}
