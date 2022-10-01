package org.xujin.janus.domain.api.valueobject;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 9:37
 **/
@Data
public class OutServiceParamTransConfig implements Serializable {

    private List<ParamMapping> paramMappings;

}
