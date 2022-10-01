package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @author chenglong.ren
 * @date 2020/5/28 11:02
 * @desc
 */
@Data
public class PublishDetailCO {

    private Long id;
    private Long applyId;
    private ReleaseTypeEnum type;
    private ReleaseStatusEnum status;
    private String publisher;
    private String clusterCode;
    private String clusterName;
    private String creator;
    private String modifier;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
    private Integer totalCount;
    private Integer completedCount;
    private Integer completionRate;
    private List<PublishIpDetailCO> publishIpDetailCOS;

}
