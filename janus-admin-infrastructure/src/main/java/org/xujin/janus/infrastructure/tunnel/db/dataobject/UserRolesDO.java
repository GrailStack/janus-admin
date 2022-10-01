package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import org.xujin.janus.infrastructure.tunnel.db.base.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户角色表(UserRolesDO)实体类
 *
 * @author yage.luan
 * @since 2019-05-09 17:53:10
 */
@Data
@Accessors(chain = true)
@TableName("t_user_roles")
public class UserRolesDO extends BaseDO {

    private String username;

    private String role;

}