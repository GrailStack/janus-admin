package org.xujin.janus.app.command.co;

import lombok.Data;

import java.util.List;

@Data
public class QueryPerminByRoleCO {

    String roleGroup;

    List<PermissionCO> permList;
}
