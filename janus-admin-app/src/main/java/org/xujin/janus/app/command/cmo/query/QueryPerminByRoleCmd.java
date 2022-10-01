package org.xujin.janus.app.command.cmo.query;


import org.xujin.janus.app.command.cmo.CommonCommand;
import lombok.Data;

@Data
public class QueryPerminByRoleCmd extends CommonCommand {

    private String roleName;

}
