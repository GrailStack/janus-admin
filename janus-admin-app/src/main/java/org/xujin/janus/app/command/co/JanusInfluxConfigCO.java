package org.xujin.janus.app.command.co;

import lombok.Data;

/**
 * @author chenglong.ren
 * @date 2020/6/11 13:45
 * @desc
 */
@Data
public class JanusInfluxConfigCO {
    private String url;
    private String userName;
    private String db;
}
