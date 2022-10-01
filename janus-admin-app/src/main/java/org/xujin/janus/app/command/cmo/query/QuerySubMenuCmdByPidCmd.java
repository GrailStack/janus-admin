package org.xujin.janus.app.command.cmo.query;


import org.xujin.janus.app.command.cmo.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QuerySubMenuCmdByPidCmd extends CommonCommand {

    private Long parentId;

}
