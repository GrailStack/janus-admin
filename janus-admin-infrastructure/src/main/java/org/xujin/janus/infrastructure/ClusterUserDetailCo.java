package org.xujin.janus.infrastructure;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/4/23 10:24
 * @desc
 */
@Data
public class ClusterUserDetailCo {
    private BigInteger id;

    private String clusterCode;

    private String userName;
    private String domainName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    private Byte isDeleted;
}
