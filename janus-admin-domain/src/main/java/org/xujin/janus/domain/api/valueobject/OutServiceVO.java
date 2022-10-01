package org.xujin.janus.domain.api.valueobject;

import org.xujin.janus.domain.api.constant.HttpMethodEnum;
import org.xujin.janus.domain.api.constant.OutServiceProtocolEnum;
import org.xujin.janus.domain.api.constant.OutServiceTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 9:38
 **/
@Data
public class OutServiceVO implements Serializable {

    private String name;

    private OutServiceTypeEnum type;

    private OutServiceProtocolEnum protocol;

    private String uri;

    private String path;

    private HttpMethodEnum method;

    private OutServiceParamTransConfig paramTransConfig;

}
