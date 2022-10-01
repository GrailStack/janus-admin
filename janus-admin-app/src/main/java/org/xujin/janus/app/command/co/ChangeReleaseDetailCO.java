package org.xujin.janus.app.command.co;

import lombok.Data;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/16 10:41
 **/
@Data
public class ChangeReleaseDetailCO {

    private Long applyId;

    private Long publishId;

    private List<PublishIpDetailCO> items;

}
