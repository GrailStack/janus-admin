package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/5/27 10:30
 * @desc
 */
@Data
public class ClusterConfigDetailCO {
    private Long id;
    private String clusterCode;
    private String configKey;
    private String configValue;
    private String name;
    private String type;
    private Byte status;
    private String clusterName;
    private String creator;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
}
