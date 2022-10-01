package org.xujin.janus.client.cmo;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: ganshitao
 * @date: 2020/5/22
 */
public class ConfigChangeCmd implements Serializable {
    public static final String method = "configChanged";

    private String requestID;
    /**
     * key:the changed config key.
     * value:the changed value object.If there are more than one, separate them with ,
     */
    private Map<String, String> changedItem;

    public ConfigChangeCmd(Map<String, String> changedItem) {
        this.changedItem = changedItem;
    }


    public Map<String, String> getChangedItem() {
        return changedItem;
    }

    public void setChangedItem(Map<String, String> changedItem) {
        this.changedItem = changedItem;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
}
