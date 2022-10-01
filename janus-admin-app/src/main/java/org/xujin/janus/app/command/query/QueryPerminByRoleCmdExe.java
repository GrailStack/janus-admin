package org.xujin.janus.app.command.query;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.query.QueryPerminByRoleCmd;
import org.xujin.janus.app.command.co.PermissionCO;
import org.xujin.janus.app.command.co.QueryPerminByRoleCO;
import org.xujin.janus.infrastructure.tunnel.db.dao.PermissionMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.RolePermissionMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PermissionDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建角色或修改角色查询对应的权限
 * @author xujin
 */
@CmdHandler
public class QueryPerminByRoleCmdExe implements CommandExecutorI<ResultData<List<QueryPerminByRoleCO>>, QueryPerminByRoleCmd> {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public ResultData<List<QueryPerminByRoleCO>> execute(QueryPerminByRoleCmd cmd) {
        ResultData<List<QueryPerminByRoleCO>> resultData = ResultData.success(null);
        List<String> perGroupList=permissionMapper.queryPerGroup();
        if(null==perGroupList||perGroupList.size()==0){
            return resultData;
        }

        List<QueryPerminByRoleCO> perminByRoleCOS=new ArrayList<>();
        for(String group:perGroupList){
            if(null==group){
                continue;
            }
            List<PermissionDO> allPermDOList= permissionMapper.queryAllPermByGroup(group);
            if(null==allPermDOList||allPermDOList.size()==0){
                continue;
            }
            //新增角色查看所有列表
            if(StringUtils.isEmpty(cmd.getRoleName())){
                AssemResult(perminByRoleCOS, group, allPermDOList);
            }else{
                QueryPerminByRoleCO queryPerminByRoleCO = new QueryPerminByRoleCO();
                List<PermissionCO> allPermisCOList=BeanMapper.mapList(allPermDOList,PermissionDO.class,PermissionCO.class);
                for (PermissionCO allPer:allPermisCOList) {
                     int count=rolePermissionMapper.countRolePermByRoleAndPid(allPer.getId(),cmd.getRoleName());
                     if(count>0){
                         allPer.setRelated(true);
                     }
                }
                queryPerminByRoleCO.setPermList(allPermisCOList);
                queryPerminByRoleCO.setRoleGroup(group);
                perminByRoleCOS.add(queryPerminByRoleCO);
            }
        }
        return resultData.setData(perminByRoleCOS);
    }

    private void AssemResult(List<QueryPerminByRoleCO> perminByRoleCOS, String group, List<PermissionDO> permissionDOList) {
        QueryPerminByRoleCO queryPerminByRoleCO = new QueryPerminByRoleCO();
        queryPerminByRoleCO.setPermList(BeanMapper.mapList(permissionDOList, PermissionDO.class, PermissionCO.class));
        queryPerminByRoleCO.setRoleGroup(group);
        perminByRoleCOS.add(queryPerminByRoleCO);
    }
}
