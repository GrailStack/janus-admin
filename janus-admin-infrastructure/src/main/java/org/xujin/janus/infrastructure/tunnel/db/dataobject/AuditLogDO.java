package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;
import lombok.Data;
import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author halo codegen
 */
@TableName("t_audit_log")
@Data
public class AuditLogDO {

    /**
     * 操作日志id
     */
    @TableId(value = "id", type = AUTO)
    private Integer id;

    /**
     * 操作人
     */
    @TableField(value = "username")
    private String username;

    /**
     * 请求方式
     */
    @TableField(value = "method")
    private String method;

    /**
     * 请求路径
     */
    @TableField(value = "url")
    private String url;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 操作时间
     */
    @TableField(value = "start_time")
    private Timestamp startTime;

    /**
     * 操作业务类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 请求参数
     */
    @TableField(value = "params")
    private String params;

    @TableField(value = "is_deleted")
    private String isDeleted;

    /**
     * 请求处理结果内容
     */
    @TableField(value = "deal_result")
    private String dealResult;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified")
    private Timestamp gmtModified;

}