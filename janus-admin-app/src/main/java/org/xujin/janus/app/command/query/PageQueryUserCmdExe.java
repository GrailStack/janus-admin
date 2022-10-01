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
import org.xujin.janus.app.command.cmo.query.PageQueryUserCmd;
import org.xujin.janus.app.command.co.UserCO;
import org.xujin.janus.infrastructure.tunnel.db.dao.RoleMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.UserMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.UserRolesMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserRolesDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CmdHandler
@Slf4j
public class PageQueryUserCmdExe implements CommandExecutorI<ResultData<PageResult<UserCO>>, PageQueryUserCmd> {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private RoleMapper roleMapper;


    @Autowired
    private UserRolesMapper userRolesMapper;


    @Override
    public ResultData<PageResult<UserCO>> execute(PageQueryUserCmd pageQueryUserCmd) {
        ResultData<PageResult<UserCO>> resultData = ResultData.success(null);
        if (!check(pageQueryUserCmd, resultData)) {
            return resultData;
        }
        try {
            IPage<UserDO> rolePage = pageGetUser(pageQueryUserCmd);
            PageResult<UserCO> pageResult = new PageResult<>();
            List<UserDO> userDOList=rolePage.getRecords();
            if(null==userDOList||userDOList.size()==0){
                return resultData;
            }
            List<UserCO> userCOS= BeanMapper.mapList(userDOList,UserDO.class,UserCO.class);
            for (UserCO userCO:userCOS) {
                UserRolesDO userRolesD=userRolesMapper.queryByUserName(userCO.getUserName());
                if(null!=userRolesD){
                    RoleDO roleDO=roleMapper.queryRoleByKey(userRolesD.getRole());
                    if(null!=roleDO){
                        userCO.setRole(roleDO.getName());
                    }
                }
            }
            pageResult.setList(userCOS);
            pageResult.setCurrentPage(rolePage.getCurrent());
            pageResult.setTotalCount(rolePage.getTotal());
            pageResult.setTotalPage(rolePage.getPages());
            resultData.setData(pageResult);
        } catch (Exception ex) {
            log.error(String.format("page query app failed, cmd=%s", pageQueryUserCmd), ex);
            resultData.setCode(500);
            resultData.setMsgContent("失败");
        }
        return resultData;
    }

    protected IPage<UserDO> pageGetUser(PageQueryUserCmd cmd) {
        Page<UserDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        if (StringUtils.isNoneBlank(cmd.getSearchKey())) {
            queryWrapper.and(qw -> qw
                    .like("`username`", cmd.getSearchKey()).or()
                    .like("name", cmd.getSearchKey()).or()
                    .like("`email`", cmd.getSearchKey()));
        }
        queryWrapper.orderByAsc("id");
        return userMapper.selectPage(page, queryWrapper);
    }

    protected boolean check(PageQueryUserCmd cmd, ResultData<PageResult<UserCO>> resultData) {
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
