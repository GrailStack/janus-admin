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
 * @date 2020/6/1 20:59
 **/
@Data
public class ApiDeleteCmd extends Command {

    @ApiModelProperty("Api正式环境ID")
    @NotNull
    @Min(1)
    private Long id;

}
