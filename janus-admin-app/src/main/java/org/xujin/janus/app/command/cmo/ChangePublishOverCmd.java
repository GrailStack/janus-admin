package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import org.xujin.janus.domain.change.constant.PublishOverActionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/17 20:38
 **/
@Data
public class ChangePublishOverCmd extends Command {

    @ApiModelProperty("publishIpId")
    @NotNull
    @Min(1)
    private Long publishIpId;

    @NotNull(message = "action不正确")
    private PublishOverActionEnum action;

}
