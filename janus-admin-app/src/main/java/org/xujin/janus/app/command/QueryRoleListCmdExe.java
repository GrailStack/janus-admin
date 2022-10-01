package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorWithoutInputI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.co.RoleCO;
import org.xujin.janus.infrastructure.tunnel.db.dao.RoleMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CmdHandler
public class QueryRoleListCmdExe implements CommandExecutorWithoutInputI<ResultData<List<RoleCO>>> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ResultData<List<RoleCO>> execute() {
        List<RoleDO> roleDOS=roleMapper.getAllRoles();
        return ResultData.success(BeanMapper.mapList(roleDOS,RoleDO.class,RoleCO.class));
    }
}
