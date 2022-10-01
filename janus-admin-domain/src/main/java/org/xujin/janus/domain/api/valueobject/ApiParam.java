package org.xujin.janus.domain.api.valueobject;

import org.xujin.janus.domain.api.constant.ApiParamDataTypeEnum;
import org.xujin.janus.domain.api.constant.ApiParamPositionEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/5 15:39
 **/
@Data
public class ApiParam implements Serializable {

    @NotBlank(message = "name of param is required")
    private String name;

    @NotNull(message = "position of param is required")
    private ApiParamPositionEnum position;

    @NotNull(message = "dataType of param is required")
    private ApiParamDataTypeEnum dataType;

    @NotNull(message = "required of param is required")
    private Boolean required;

    private String defaultValue;

    private String description;

}
