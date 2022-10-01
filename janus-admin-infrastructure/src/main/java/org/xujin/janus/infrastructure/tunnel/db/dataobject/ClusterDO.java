package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigInteger;
import java.sql.Timestamp;
import lombok.Data;
import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author halo codegen
 */
@TableName("t_cluster")
@Data
public class ClusterDO {

    /**
     * 集群Id
     */
    @TableId(value = "id", type = AUTO)
    private BigInteger id;

    /**
     * 集群编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 集群中文名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 负责人姓名
     */
    @TableField(value = "owner_name")
    private String ownerName;

    /**
     * 负责人姓名
     */
    @TableField(value = "domain_name")
    private String domainName;

    /**
     * 业务名称,包括所有父级业务名称
     */
    @TableField(value = "biz_name")
    private String bizName;

    /**
     * 负责人Id
     */
    @TableField(value = "owner_id")
    private String ownerId;


    @TableField(value = "status")
    private Byte status;

    /**
     * 集群描述
     */
    @TableField(value = "description")
    private String description;

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
     * 是否共享集群 0 独立集群 1 共享集群
     */
    @TableField(value = "is_share")
    private Byte isShare;

    /**
     * 是否删除 0 否 1 已经删除
     */
    @TableField(value = "is_deleted")
    private Byte isDeleted;

    /**
     * 集群创建人
     */
    @TableField(value = "created")
    private String created;

    /**
     * 集群更新人
     */
    @TableField(value = "updated")
    private String updated;

    @TableField(value = "stars")
    private Double stars;

}