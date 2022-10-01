package org.xujin.janus.client.co.api;

import org.xujin.halo.dto.DTO;

import java.io.Serializable;
import java.util.Map;

/**
 * API中的断言序列化载体
 * @author xujin
 */
public class PredicatesCO implements Serializable {


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
