package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/16 10:40
 **/
@Data
public class ChangeReleaseDetailQueryCmd extends Command {

    @ApiModelProperty("变更申请ID")
    @NotNull
    @Min(1)
    private Long applyId;

    @ApiModelProperty("变更下发类型")
    @NotNull(message = "变更下发类型不正确")
    private ReleaseTypeEnum type;

}
