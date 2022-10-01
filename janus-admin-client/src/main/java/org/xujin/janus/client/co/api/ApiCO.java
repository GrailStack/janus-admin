package org.xujin.janus.client.co.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * API中序列化载体
 * @author xujin
 */
public class ApiCO implements Serializable {

    private String id;

    private List<PredicatesCO> predicates;

    private List<FilterCO> filters;

    /**
     * 协议类型
     */
    private String protocol;

    /**
     * 如果协议是lb://sc,ServiceName就是服务名
     */
    private  String serviceName;


    private List<String> hosts;


    private Map<String, Object> metadata;

    private int order = 0;

    private String loadBalancerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PredicatesCO> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<PredicatesCO> predicates) {
        this.predicates = predicates;
    }

    public List<FilterCO> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterCO> filters) {
        this.filters = filters;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getLoadBalancerName() {
        return loadBalancerName;
    }

    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}
