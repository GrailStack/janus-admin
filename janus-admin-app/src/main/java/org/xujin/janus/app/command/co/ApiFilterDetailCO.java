package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:31
 * @desc
 */
@Data
public class ApiFilterDetailCO {
    private Long id;

    private String apiId;

    private String filterId;

    private String clusterCode;

    private String filterName;

    private String filterCode;

    private String filterParam;

    private String creator;

    private String modifier;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtModified;


}
