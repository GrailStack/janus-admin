package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import org.xujin.janus.domain.change.constant.AuditActionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 13:46
 **/
@Data
public class ApplyAuditCmd extends Command {

    @ApiModelProperty("变更申请ID")
    @NotNull
    @Min(1)
    private Long applyId;

    @ApiModelProperty("操作类型")
    @NotNull(message = "action不正确")
    private AuditActionEnum action;

}
