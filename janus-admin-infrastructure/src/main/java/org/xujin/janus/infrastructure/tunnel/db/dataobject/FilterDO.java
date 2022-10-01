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
@TableName("t_filter")
@Data
public class FilterDO {

    /**
     * id
     */
    @TableId(value = "id", type = AUTO)
    private BigInteger id;

    /**
     * Filter名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 是否全局Filter 0 否 1 是
     */
    @TableField(value = "is_global")
    private Byte isGlobal;

    /**
     * 分类code
     */
    @TableField(value = "class_code")
    private String classCode;

    @TableField(value = "filter_code")
    private String filterCode;

    @TableField(value = "execute_place")
    private String executePlace;

    /**
     * 是否所有集群共享Filter 0 独立Filter 1 共享Filter
     */
    @TableField(value = "is_share")
    private Byte isShare;

    /**
     * filter初始化时的参数
     */
    @TableField(value = "filter_args")
    private String filterArgs;

    /**
     * Filter的执行顺序
     */
    @TableField(value = "filter_order")
    private Integer filterOrder;


    @TableField(value = "description")
    private String description;

    @TableField(value = "status")
    private String status;

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