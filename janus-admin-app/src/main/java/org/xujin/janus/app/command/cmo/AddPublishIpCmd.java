package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:53
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("新增下发ip参数")
public class AddPublishIpCmd extends CommonCommand {

    private Long publishId;

    /**
     * 记录灰度下发IP
     */
    private List<String> ips;


}
