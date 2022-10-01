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
@TableName("t_alarm")
@Data
public class AlarmDO {

    /**
     * id
     */
    @TableId(value = "id", type = AUTO)
    private Long id;

    /**
     * 集群编码
     */
    @TableField(value = "cluster_code")
    private String clusterCode;

    /**
     * 网关实例的ip:port
     */
    @TableField(value = "instance_host")
    private String instanceHost;

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

    /**
     * 
     */
    @TableField(value = "alarm_info")
    private String alarmInfo;

    /**
     * 告警的处理状态：0 未处理 1 已处理 2 忽略
     */
    @TableField(value = "status")
    private Byte status;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    @TableField(value = "is_deleted")
    private Byte isDeleted;

}