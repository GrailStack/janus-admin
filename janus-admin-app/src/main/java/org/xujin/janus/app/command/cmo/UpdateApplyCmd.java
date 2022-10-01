package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("更新审批参数")
public class UpdateApplyCmd extends CommonCommand {
    @ApiModelProperty("审批id")
    private BigInteger id;

    /**
     * 状态,1:审核中 2:审核通过 3:已下发  4:拒绝 5:撤回
     */
    private Byte status;
}


