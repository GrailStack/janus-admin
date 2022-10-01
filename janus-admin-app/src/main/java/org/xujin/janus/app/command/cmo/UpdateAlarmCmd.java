package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:43
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("更新告警参数")
public class UpdateAlarmCmd extends CommonCommand {
    @ApiModelProperty("唯一ID")
    private Long id;

    private Byte status;
}
