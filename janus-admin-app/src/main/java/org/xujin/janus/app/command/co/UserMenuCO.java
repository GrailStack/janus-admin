package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author yage.luan
 * created at 2019/5/11 17:32
 **/
@Data
@Accessors(chain = true)
public class UserMenuCO implements Serializable {

    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private String icon;

    private String menuKey;

    private Integer sort;

    private List<String> roleList;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtModified;

    private List<UserMenuCO> subMenus;
}
