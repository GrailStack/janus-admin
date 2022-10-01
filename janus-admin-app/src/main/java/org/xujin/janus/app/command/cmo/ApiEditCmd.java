package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import org.xujin.janus.domain.api.constant.ApiParamTransTypeEnum;
import org.xujin.janus.domain.api.constant.ApiProtocolEnum;
import org.xujin.janus.domain.api.valueobject.ApiParamTransConfig;
import org.xujin.janus.domain.api.valueobject.OutServiceConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 14:08
 **/
@Data
public class ApiEditCmd extends Command {

    @ApiModelProperty("Api正式环境ID")
    @NotNull
    @Min(1)
    private Long id;

    @ApiModelProperty("Api描述")
    @NotBlank(message = "description of api is required")
    @Size(min = 3, max = 120)
    private String description;

    @ApiModelProperty("Api协议")
    @NotNull(message = "protocol of api is required")
    private ApiProtocolEnum protocol;

    @ApiModelProperty("Api入参传递模式")
    @NotNull(message = "paramTransType of api is required")
    private ApiParamTransTypeEnum paramTransType;

    @Valid
    @ApiModelProperty("Api入参传递配置")
    @NotNull(message = "paramTransConfig of api is required")
    private ApiParamTransConfig paramTransConfig;

    @Valid
    @ApiModelProperty("后端服务配置")
    @NotNull(message = "outServiceConfig of api is required")
    private OutServiceConfig outServiceConfig;

}
