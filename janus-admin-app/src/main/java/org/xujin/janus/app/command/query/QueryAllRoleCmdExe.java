package org.xujin.janus.app.command.query;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorWithoutInputI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.co.RoleCO;
import org.xujin.janus.app.converter.RoleClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.RoleMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 17:17
 **/
@Slf4j
@CmdHandler
public class QueryAllRoleCmdExe implements CommandExecutorWithoutInputI<ResultData<List<RoleCO>>> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleClientConverter roleClientConverter;

    @Override
    public ResultData<List<RoleCO>> execute() {
        List<RoleDO> roleDOList = roleMapper.getAllRoles();
        List<RoleCO> allRoles = roleClientConverter.dataToClient(roleDOList);
        return ResultData.success(allRoles);
    }

}
