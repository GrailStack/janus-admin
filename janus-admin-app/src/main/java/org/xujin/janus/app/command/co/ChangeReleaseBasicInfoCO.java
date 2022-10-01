package org.xujin.janus.app.command.co;

import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import lombok.Data;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/16 16:53
 **/
@Data
public class ChangeReleaseBasicInfoCO {

    private Long applyId;

    private ApplyStatusEnum status;

    private String creator;

    private String approver;

    private String description;

    private String clusterCode;

    private ReleaseTypeEnum type;

    private List<String> ipList;

    private List<String> selectedIpList;

}
