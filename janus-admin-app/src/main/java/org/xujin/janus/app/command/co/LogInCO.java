package org.xujin.janus.app.command.co;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yage.luan
 * created at 2019/5/22 17:21
 **/
@Data
@Accessors(chain = true)
public class LogInCO implements Serializable {
    private String token;
    private String username;
}
