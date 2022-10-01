package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import org.xujin.janus.app.constant.ApiConstant;
import org.xujin.janus.domain.api.constant.ApiParamTransTypeEnum;
import org.xujin.janus.domain.api.constant.ApiProtocolEnum;
import org.xujin.janus.domain.api.constant.HttpMethodEnum;
import org.xujin.janus.domain.api.valueobject.ApiParamTransConfig;
import org.xujin.janus.domain.api.valueobject.OutServiceConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 10:37
 **/
@Data
public class ApiCreateCmd extends Command {

    @ApiModelProperty("Api名称，集群内唯一")
    @NotBlank(message = "name of api is required")
    private String name;

    @ApiModelProperty("Api描述")
    @NotBlank(message = "description of api is required")
    @Size(min = 3, max = 120)
    private String description;

    @ApiModelProperty("Api所属集群编码")
    @NotBlank(message = "clusterCode of api is required")
    @Size(min = 1, max = 64)
    private String clusterCode;

    @ApiModelProperty("Api协议")
    @NotNull(message = "protocol of api is required")
    private ApiProtocolEnum protocol;

    @ApiModelProperty("Api请求路径")
    @NotBlank(message = "path of api is required")
    @Pattern(regexp = ApiConstant.API_PATH_PATTERN, message = "format of path invalid")
    @Size(max = 150)
    private String path;

    @ApiModelProperty("Api请求方法")
    @NotNull(message = "method of api is required")
    private HttpMethodEnum method;

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
