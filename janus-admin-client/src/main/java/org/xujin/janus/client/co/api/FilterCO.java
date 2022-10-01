package org.xujin.janus.client.co.api;

import java.io.Serializable;
import java.util.Map;

/**
 * API中的Filter序列化载体
 * @author xujin
 */
public class FilterCO implements Serializable {
    private String name;
    private Map<String, String> args;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
