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
@TableName("t_api_filter")
@Data
public class ApiFilterDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = AUTO)
    private Integer id;

    /**
     * api Id
     */
    @TableField(value = "api_id")
    private String apiId;

    /**
     * 该表逻辑主键
     */
    @TableField(value = "filter_id")
    private String filterId;

    /**
     * 集群编码
     */
    @TableField(value = "cluster_code")
    private String clusterCode;

    /**
     * Filter中文名
     */
    @TableField(value = "filter_name")
    private String filterName;

    /**
     * Filter类名
     */
    @TableField(value = "filter_code")
    private String filterCode;

    /**
     * filter参数,json类型
     */
    @TableField(value = "filter_param")
    private String filterParam;

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

}