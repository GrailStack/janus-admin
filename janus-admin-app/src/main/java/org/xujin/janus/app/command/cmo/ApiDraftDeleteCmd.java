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
 * @date 2020/6/2 13:46
 **/
@Data
public class ApiDraftDeleteCmd extends Command {

    @ApiModelProperty("Api草稿环境ID")
    @NotNull
    @Min(1)
    private Long id;

}
