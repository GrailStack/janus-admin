package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/16 16:51
 **/
@Data
public class ChangeReleaseBasicInfoQueryCmd extends Command {

    @ApiModelProperty("变更申请ID")
    @NotNull
    @Min(1)
    private Long applyId;

}
