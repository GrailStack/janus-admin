package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:31
 * @desc
 */
@Data
public class AlarmDetailCO {
    private Long id;

    private String clusterCode;

    private String instanceHost;

    private String alarmInfo;

    private Byte status;

    private String clusterName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtCreate;
}
