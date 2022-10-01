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
@TableName("t_role_permission")
@Data
public class RolePermissionDO {

    /**
     *
     */
    @TableId(value = "id", type = AUTO)
    private Long id;

    /**
     * 角色
     */
    @TableField(value = "role")
    private String role;

    /**
     * 权限ID
     *
     * @see PermissionDO
     */
    @TableField(value = "permission_id")
    private Long permissionId;

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