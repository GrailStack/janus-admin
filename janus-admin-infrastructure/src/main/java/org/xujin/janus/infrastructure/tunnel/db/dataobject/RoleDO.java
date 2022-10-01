package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author halo codegen
 */
@TableName("t_role")
@Data
public class RoleDO  implements Serializable {

    /**
     *
     */
    @TableId(value = "id", type = AUTO)
    private Long id;


    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;



    /**
     *
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    private String desc;

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