package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yage.luan
 * created at 2019/5/11 17:37
 **/
@Data
@Accessors(chain = true)
public class QueryUserMenuCmd extends CommonCommand {

    private String userName;

}


