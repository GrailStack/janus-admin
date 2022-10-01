package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 17:31
 **/
@Data
public class ChangeCollectCmd extends Command {

    @ApiModelProperty("集群编码")
    @NotBlank
    @Size(min = 1, max = 64)
    private String clusterCode;

}
