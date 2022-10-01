package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 18:00
 **/
@Data
public class RoleCO implements Serializable {

    private Long id;

    private String key;

    private String name;

    private String desc;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtModified;

    private Map<String, List<PermissionCO>> perMissMap;

}
