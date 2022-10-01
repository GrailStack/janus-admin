package org.xujin.janus.domain.api.valueobject;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/8 15:18
 **/
@Data
public class ServiceAddress implements Serializable {

    private String host;

    private Integer port;

}
