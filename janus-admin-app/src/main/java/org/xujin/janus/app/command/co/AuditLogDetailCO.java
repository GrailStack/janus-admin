package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:31
 * @desc
 */
@Data
public class AuditLogDetailCO {
    private BigInteger id;

    private String username;

    private String method;

    private String url;

    private String ip;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    private String type;

    private String params;

    private String dealResult;



}
