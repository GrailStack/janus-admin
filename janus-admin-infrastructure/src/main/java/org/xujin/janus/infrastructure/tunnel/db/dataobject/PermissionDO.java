package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author halo codegen
 */
@TableName("t_permission")
@Data
public class PermissionDO {

    /**
     *
     */
    @TableId(value = "id", type = AUTO)
    private Long id;

    /**
     * 权限字符串：Shiro WildcardPermission格式
     *
     * @see org.apache.shiro.authz.permission.WildcardPermission
     */
    @TableField(value = "permission")
    private String permission;

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

    /**
     * 描述
     */
    @TableField(value = "`group`")
    private String group;

}