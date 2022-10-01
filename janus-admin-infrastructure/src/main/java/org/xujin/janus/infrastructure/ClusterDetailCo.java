package org.xujin.janus.infrastructure;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/4/15 11:15
 * @desc
 */
@Data
public class ClusterDetailCo {

    private BigInteger id;

    private String code;

    private String name;

    private String ownerName;

    private String bizName;
    private String domainName;

    private String ownerId;

    private String state;

    private String description;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    private Byte isShare;

    private Byte isDeleted;

    private String created;

    private String updated;

    @ApiModelProperty("告警次数")
    private Integer alarm;

    @ApiModelProperty("实例数")
    private Integer instance;

    @ApiModelProperty("配置待审批数")
    private Integer change;

    @ApiModelProperty("分数")
    private Double stars;

}
