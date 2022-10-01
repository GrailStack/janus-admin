package org.xujin.janus.app.command.co;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/8 15:18
 **/
@Data
public class HostVO implements Serializable {

    private String host;

    private String port;

}
