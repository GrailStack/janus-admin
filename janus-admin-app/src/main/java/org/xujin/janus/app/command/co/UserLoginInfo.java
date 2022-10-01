package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/6/23 13:32
 * @desc
 */
@Data
public class UserLoginInfo {
    private String name;
    private String email;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date currentTime;
    private String version;

}
