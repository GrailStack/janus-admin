package org.xujin.janus.client.co.api;

import java.util.List;
import java.util.Map;

/**
 * @author: gan
 * @date: 2020/4/17
 */
public class RouteConfig {
    private String id;

    private List<PredicatesCO> predicates;

    private List<FilterCO> filters;

    private String uri;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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
}
