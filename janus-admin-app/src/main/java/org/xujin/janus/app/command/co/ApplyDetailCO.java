package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import lombok.Data;

import java.util.Date;


/**
 * @author chenglong.ren
 * @date 2020/5/26 17:31
 * @desc
 */
@Data
public class ApplyDetailCO {

    private Long id;

    private String clusterCode;

    protected String clusterName;

    private String description;

    private String request;

    private String changes;

    private ApplyStatusEnum status;

    private String creator;

    private String approver;

    private String publisher;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

}
