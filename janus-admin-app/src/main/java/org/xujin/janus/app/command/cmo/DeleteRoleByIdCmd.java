package org.xujin.janus.app.command.cmo;


import lombok.Data;

@Data
public class DeleteRoleByIdCmd extends CommonCommand {

    /**
     * 根据id删除
     */
    private Long id;

}
