package org.xujin.janus.client.cmo;

/**
 * @author: gan
 * @date: 2020/6/12
 */
public class RouteChangeDTO {
    private String routeId;

    private RouteChangeTypeEnum routeChangeType;
    /**
     * 操作类型
     */
    private OperationEnum operation;

    /**
     * {
     * "id": "zmGuide",
     * "predicates": [{
     * "name": "Path",
     * "args": {
     * "pattern": "^/zmGuide/.*"
     * }
     * }],
     * "filters": [{
     * "name": "StripPrefix",
     * "args": {
     * "parts": "1"
     * }
     * }],
     * "uri": "http://guide.zmops.cc/tech/#/link",
     * "metadata": {},
     * "order": 0
     * }
     */

    private String dataJson;

    public OperationEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation = operation;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public RouteChangeTypeEnum getRouteChangeType() {
        return routeChangeType;
    }

    public void setRouteChangeType(RouteChangeTypeEnum routeChangeType) {
        this.routeChangeType = routeChangeType;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
}
