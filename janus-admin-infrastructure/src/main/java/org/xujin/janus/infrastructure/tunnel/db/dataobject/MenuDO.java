package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import org.xujin.janus.infrastructure.tunnel.db.base.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单表(MenuDO)实体类
 *
 * @author yage.luan
 * @since 2019-05-09 17:43:51
 */
@Data
@Accessors(chain = true)
@TableName("t_menu")
public class MenuDO extends BaseDO {
    //菜单父Id
    private Long parentId;
    //菜单名称
    private String name;
    //url
    private String url;
    //角色
    private String roles;
    //菜单排序
    private Integer sort;
    //图标
    private String icon;
    //菜单key
    private String menuKey;

}