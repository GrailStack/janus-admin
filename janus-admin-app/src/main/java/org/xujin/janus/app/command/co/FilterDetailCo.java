package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:53
 * @desc
 */
@Data
public class FilterDetailCo {

    private BigInteger id;

    private String name;

    private Byte isGlobal;

    private Byte isShare;

    private String filterArgs;

    private Integer filterOrder;

    private String creator;

    private String modifier;

    private String classCode;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    private Byte isDeleted;

    private String description;
    private String status;

    private String filterCode;

    private String executePlace;

    private String modelType;
}
