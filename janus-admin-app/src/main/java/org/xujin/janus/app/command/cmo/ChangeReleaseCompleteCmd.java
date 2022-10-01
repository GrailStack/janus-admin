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
 * @date 2020/6/12 16:07
 **/
@Data
public class ChangeReleaseCompleteCmd extends Command {

    @ApiModelProperty("发布ID")
    @NotNull
    @Min(1)
    private Long publishId;

}
