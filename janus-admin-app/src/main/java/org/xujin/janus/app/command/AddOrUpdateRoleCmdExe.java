package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddOrUpdateRoleCmd;
import org.xujin.janus.domain.user.entity.RoleE;
import org.xujin.janus.domain.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

@CmdHandler
public class AddOrUpdateRoleCmdExe implements CommandExecutorI<ResultData<Void>, AddOrUpdateRoleCmd> {


    @Autowired
    private RoleService roleService;

    @Override
    public ResultData<Void> execute(AddOrUpdateRoleCmd cmd) {
       RoleE role=new RoleE();
       role.setKey(cmd.getKey());
       role.setId(cmd.getRoleId());
       role.setDesc(cmd.getDesc());
       role.setName(cmd.getName());
       roleService.saveOrUpdateRole(role,cmd.getPIds());
       return ResultData.success(null);
    }
}
