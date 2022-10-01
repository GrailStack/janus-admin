package org.xujin.janus.client.co.api;

import java.time.Duration;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/5/18 14:51
 **/
public class JanusInfluxConfig {


    /**
     * Login user of the Influx server.
     */
    private String userName;


    /**
     * The db to send metrics to.
     */
    private String db = "janus";


    /**
     * URI of the Influx server.
     */
    private String uri = "http://localhost:8086";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
