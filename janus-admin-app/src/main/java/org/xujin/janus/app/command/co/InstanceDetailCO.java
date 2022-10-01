package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:31
 * @desc
 */
@Data
public class InstanceDetailCO {
    private Long id;

    private String clusterCode;

    private String host;

    private String clusterName;
    private String ownerName;
    private Integer alarmCount;

    private Byte status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtModified;

}
