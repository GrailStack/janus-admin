package org.xujin.janus.domain.api.valueobject;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/5 15:33
 **/
@Data
public class ApiParamTransConfig implements Serializable {

    @NotNull(message = "params of ApiParamTransConfig is required")
    private List<@Valid ApiParam> params;

}


