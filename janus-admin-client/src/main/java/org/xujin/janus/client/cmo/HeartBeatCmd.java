package org.xujin.janus.client.cmo;

import java.io.Serializable;

/**
 * @author: ganshitao
 * @date: 2020/5/22
 */
public class HeartBeatCmd implements Serializable {
    public static final String method = "heartBeat";
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
