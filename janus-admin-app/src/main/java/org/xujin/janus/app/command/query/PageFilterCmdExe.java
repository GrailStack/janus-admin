package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PageFilterCmd;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.domain.user.ports.ClusterFilterPort;
import org.xujin.janus.domain.user.ports.ClusterUserPort;
import org.xujin.janus.domain.user.ports.UserRolePort;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/15 13:48
 * @desc
 */
@Slf4j
@CmdHandler
public class PageFilterCmdExe implements CommandExecutorI<ResultData<PageResult<FilterDetailCo>>, PageFilterCmd> {

    @Resource
    FilterMapper filterMapper;

    @Resource
    FilterClientConverter converter;

    @Resource
    UserRolePort userRolePort;

    @Autowired
    ClusterUserPort clusterUserPort;

    @Autowired
    ClusterFilterPort clusterFilterPort;


    @Override
    public ResultData<PageResult<FilterDetailCo>> execute(PageFilterCmd cmd) {
        ResultData<PageResult<FilterDetailCo>> resultData = ResultData.success(null);
        check(cmd);
        IPage<FilterDO>  page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        boolean admin = userRolePort.isAdmin(cmd.getUserName());
        if (!admin) {
            //非超管,只能查看自己可见的集群关联的filter
            page = pageUserFilter(cmd);
        } else {
            page = page(cmd);
        }
        PageResult<FilterDetailCo> pageResult = new PageResult<>();
        pageResult.setList(converter.dataToClients(page.getRecords()));
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        resultData.setData(pageResult);
        return resultData;
    }

    protected boolean check(PageFilterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        return true;
    }

    public IPage<FilterDO> page(PageFilterCmd cmd) {
        Page<FilterDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<FilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        if (!StringUtils.isEmpty(cmd.getFilterName())) {
            queryWrapper.like("name", cmd.getFilterName());
        }
        if (!StringUtils.isEmpty(cmd.getClassCode())) {
            queryWrapper.eq("class_code", cmd.getClassCode());
        }

        if (!StringUtils.isEmpty(cmd.getClassType())) {
            queryWrapper.eq("class_type", cmd.getClassType());
        }

        if (!StringUtils.isEmpty(cmd.getFilterCode())) {
            queryWrapper.eq("filter_code", cmd.getFilterCode());
        }

        if (!StringUtils.isEmpty(cmd.getStatus())) {
            queryWrapper.eq("status", cmd.getStatus());
        }
        queryWrapper.orderByDesc("id");
        return filterMapper.selectPage(page, queryWrapper);
    }

    public IPage<FilterDO> pageUserFilter(PageFilterCmd cmd) {
        Page<FilterDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        //先查询用户与集群的绑定
        List<String> codeByUserName = clusterUserPort.findClusterCodeByUserName(cmd.getUserName());
        //再查询集群与filter的绑定
        List<Integer> bigIntegers = clusterFilterPort.listFilterIdByClusterCodes(codeByUserName);
        //最终查询出来非管理员可见的filter
        QueryWrapper<FilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        if (!CollectionUtils.isEmpty(bigIntegers)) {
            queryWrapper.in("id", bigIntegers);
        }
        if (!StringUtils.isEmpty(cmd.getClassCode())) {
            queryWrapper.eq("class_code", cmd.getClassCode());
        }
        queryWrapper.orderByDesc("id");
        return filterMapper.selectPage(page, queryWrapper);
    }
}
