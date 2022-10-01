package org.xujin.janus.app.command.query;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.query.PageQueryRoleCmd;
import org.xujin.janus.app.command.co.PermissionCO;
import org.xujin.janus.app.command.co.RoleCO;
import org.xujin.janus.infrastructure.tunnel.db.dao.PermissionMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.RoleMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PermissionDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询角色列表
 * @author xujin
 */
@CmdHandler
@Slf4j
public class PageQueryRoleCmdExe implements CommandExecutorI<ResultData<PageResult<RoleCO>>, PageQueryRoleCmd> {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public ResultData<PageResult<RoleCO>> execute(PageQueryRoleCmd pageQueryRoleCmd) {
        ResultData<PageResult<RoleCO>> resultData = ResultData.success(null);
        if (!check(pageQueryRoleCmd, resultData)) {
            return resultData;
        }
        try {
            IPage<RoleDO> rolePage = pageGetRole(pageQueryRoleCmd);
            PageResult<RoleCO> pageResult = new PageResult<>();
            List<RoleDO> roleDOList=rolePage.getRecords();
            if(null==roleDOList||roleDOList.size()==0){
                return resultData;
            }
            List<RoleCO> roleCos=BeanMapper.mapList(roleDOList,RoleDO.class,RoleCO.class);
            List<String> perGroupList=permissionMapper.queryPerGroup();
            if(null==perGroupList||perGroupList.size()==0){
                pageResult.setList(roleCos);
                resultData.setData(pageResult);
                return resultData;
            }
            for (RoleCO role:roleCos) {
                Map<String, List<PermissionCO>> perMissMap=new HashMap<String, List<PermissionCO>>() ;
                for(String group:perGroupList){
                    if(null==group){
                        continue;
                    }
                    List<PermissionDO> permissionDOList=permissionMapper.queryPermissionByRoleAndGroup(group,role.getKey());
                    if(null==permissionDOList||permissionDOList.size()==0){
                        continue;
                    }
                    perMissMap.put(group,BeanMapper.mapList(permissionDOList,PermissionDO.class,PermissionCO.class));
                }
                role.setPerMissMap(perMissMap);
            }
            pageResult.setList(roleCos);
            pageResult.setCurrentPage(rolePage.getCurrent());
            pageResult.setTotalCount(rolePage.getTotal());
            pageResult.setTotalPage(rolePage.getPages());
            resultData.setData(pageResult);
        } catch (Exception ex) {
            log.error(String.format("page query app failed, cmd=%s", pageQueryRoleCmd), ex);
            resultData.setCode(500);
            resultData.setMsgContent("失败");
    }
        return resultData;
    }

    protected IPage<RoleDO> pageGetRole(PageQueryRoleCmd cmd) {
        Page<RoleDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<RoleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        if (StringUtils.isNoneBlank(cmd.getSearchKey())) {
            queryWrapper.and(qw -> qw
                    .like("`key`", cmd.getSearchKey()).or()
                    .like("name", cmd.getSearchKey()).or()
                    .like("`desc`", cmd.getSearchKey()));
        }
        queryWrapper.orderByAsc("id");
        return roleMapper.selectPage(page, queryWrapper);
    }

    protected boolean check(PageQueryRoleCmd cmd, ResultData<PageResult<RoleCO>> resultData) {
        try {
            Preconditions.checkNotNull(cmd, "cmd不能为null");
            Preconditions.checkNotNull(cmd.getPageNo(), "pageNo不能为null");
            Preconditions.checkNotNull(cmd.getPageSize(), "pageSize不能为null");
            Preconditions.checkArgument(cmd.getPageNo() >= 1, "pageNo不能小于1");
            Preconditions.checkArgument(cmd.getPageSize() >= 1, "pageSize不能小于1");
            Preconditions.checkArgument(cmd.getPageSize() <= 500, "pageSize不能大于500");
        } catch (Exception ex) {
            resultData.setMsgContent(ex.getMessage());
            return false;
        }
        return true;
    }
}
