package org.xujin.janus.client.cmo;

import java.io.Serializable;

/**
 * @author: ganshitao
 * @date: 2020/5/22
 */
public class AlarmCmd implements Serializable {
    public static final String method = "alarm";

    /**
     * ip:port
     */
    private String host;

    /**
     * 告警信息
     */
    private String alarmInfo;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAlarmInfo() {
        return alarmInfo;
    }

    public void setAlarmInfo(String alarmInfo) {
        this.alarmInfo = alarmInfo;
    }
}
