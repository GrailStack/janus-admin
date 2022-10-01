package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/5/28 11:02
 * @desc
 */
@Data
public class PublishIpDetailCO {

    private Long id;

    private Long publishId;

    private String ip;

    private ReleaseTypeEnum type;

    private ReleaseStatusEnum status;

    private String creator;

    private String modifier;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

}
