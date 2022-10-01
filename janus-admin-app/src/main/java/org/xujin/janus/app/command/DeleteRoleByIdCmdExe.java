package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.DeleteRoleByIdCmd;
import org.xujin.janus.domain.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

@CmdHandler
public class DeleteRoleByIdCmdExe implements CommandExecutorI<ResultData, DeleteRoleByIdCmd> {

    @Autowired
    private RoleService roleService;

    @Override
    public ResultData execute(DeleteRoleByIdCmd cmd) {
        roleService.deleteRole(cmd.getId());
        return ResultData.success(true);
    }

}
