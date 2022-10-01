package org.xujin.janus.client.cmo;

import java.io.Serializable;

/**
 * 修改Janus Instance是否Online
 * @author xujin
 */
public class ChangeOnlineCmd implements Serializable {

    public static final String method = "changeOnline";

    /**
     * online为true，让其在线。
     * 为false让其优雅下线
     */
    private  boolean online;

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
