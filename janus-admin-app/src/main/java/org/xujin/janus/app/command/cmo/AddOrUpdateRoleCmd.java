package org.xujin.janus.app.command.cmo;


import lombok.Data;

import java.util.List;


@Data
public class AddOrUpdateRoleCmd extends CommonCommand {

    private Long roleId;

    private String key;

    private String name;

    private String desc;

    private List<Long> pIds;

}
