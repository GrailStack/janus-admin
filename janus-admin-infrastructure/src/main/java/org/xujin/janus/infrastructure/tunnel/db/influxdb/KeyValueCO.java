package org.xujin.janus.infrastructure.tunnel.db.influxdb;

import lombok.Data;

/**
 * @author chenglong.ren
 * @date 2020/6/10 17:44
 * @desc
 */
@Data
public class KeyValueCO {
    private Object key;
    private Object value;
}
