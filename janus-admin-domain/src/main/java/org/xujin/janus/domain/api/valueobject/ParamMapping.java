package org.xujin.janus.domain.api.valueobject;

import org.xujin.janus.domain.api.constant.ApiParamDataTypeEnum;
import org.xujin.janus.domain.api.constant.ApiParamPositionEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 10:55
 **/
@Data
public class ParamMapping implements Serializable {

    private String name;

    private ApiParamPositionEnum position;

    private ApiParamDataTypeEnum dataType;

    private String defaultValue;

    private Boolean required;

    private String description;

    private String afterName;

    private ApiParamPositionEnum afterPosition;

}
