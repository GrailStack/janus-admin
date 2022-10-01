package org.xujin.janus.client.co;

import java.io.Serializable;

/**
 *
 * @author: ganshitao
 * @date: 2020/5/22
 */
public class AllConfigCO implements Serializable {

    private String configJson;

    public String getConfigJson() {
        return configJson;
    }

    public void setConfigJson(String configJson) {
        this.configJson = configJson;
    }
}
