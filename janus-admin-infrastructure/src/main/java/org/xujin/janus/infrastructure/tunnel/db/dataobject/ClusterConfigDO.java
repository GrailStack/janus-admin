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
@TableName("t_cluster_config")
@Data
public class ClusterConfigDO {

    /**
     * id
     */
    @TableId(value = "id", type = AUTO)
    private BigInteger id;

    /**
     * 集群编码
     */
    @TableField(value = "cluster_code")
    private String clusterCode;

    /**
     * 配置key
     */
    @TableField(value = "config_key")
    private String configKey;

    /**
     * 配置Value
     */
    @TableField(value = "config_value")
    private String configValue;

    @TableField(value = "name")
    private String name;

    @TableField(value = "type")
    private String type;

    @TableField(value = "status")
    private Byte status;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 修改者
     */
    @TableField(value = "modifier")
    private String modifier;

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
     * 是否删除 0 否 1 已经删除
     */
    @TableField(value = "is_deleted")
    private Byte isDeleted;

}