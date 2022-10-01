package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PageClusterCmd;
import org.xujin.janus.app.converter.ClusterClientConverter;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.domain.user.ports.ClusterUserPort;
import org.xujin.janus.domain.user.ports.UserRolePort;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PageClusterCmdExe implements CommandExecutorI<ResultData<PageResult<ClusterDetailCo>>, PageClusterCmd> {

    @Resource
    ClusterMapper clusterMapper;

    @Autowired
    private UserRolePort userRolePort;

    @Resource
    ClusterClientConverter converter;

    @Autowired
    private ClusterUserPort clusterUserPort;

    @Override
    public ResultData<PageResult<ClusterDetailCo>> execute(PageClusterCmd cmd) {
        ResultData<PageResult<ClusterDetailCo>> resultData = ResultData.success(null);
        check(cmd);
        boolean admin = userRolePort.isAdmin(cmd.getUserName());
        IPage<ClusterDO> page = new Page<>();
        if (!admin&&HaloConstant.ME.equals(cmd.getFilter())) {
            //查询自己可见的集群
            page = pageUserClusters(cmd);
        } else {
            //管理员 查询所有集群
            page = page(cmd);
        }
        PageResult<ClusterDetailCo> pageResult = new PageResult<>();
        List<ClusterDetailCo> list = converter.dataToClients(page.getRecords());
        if (CollectionUtils.isEmpty(list)) {
            resultData.setMsgContent("您暂时没有可用集群");
            return resultData;
        }
        pageResult.setList(list);
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        resultData.setData(pageResult);
        return resultData;
    }

    protected boolean check(PageClusterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getUserName(), "userName不能为null");
        return true;
    }

    public IPage<ClusterDO> page(PageClusterCmd cmd) {
        Page<ClusterDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        if (!StringUtils.isEmpty(cmd.getBizName())) {
            queryWrapper.like("biz_name", cmd.getBizName());
        }
        if (!StringUtils.isEmpty(cmd.getName())) {
            queryWrapper.like("name", cmd.getName());
        }
        if (!StringUtils.isEmpty(cmd.getKeyWord())) {
            queryWrapper.and(wrapper -> wrapper
                    .like("name", cmd.getKeyWord()).or()
                    .like("code", cmd.getKeyWord()));
        }
        queryWrapper.orderByDesc("id");
        return clusterMapper.selectPage(page, queryWrapper);
    }

    public IPage<ClusterDO> pageUserClusters(PageClusterCmd cmd) {
        List<String> codeByUserName = clusterUserPort.findClusterCodeByUserName(cmd.getUserName());
        Page<ClusterDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        if (CollectionUtils.isEmpty(codeByUserName)) {
            return page;
        }
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        if (CollectionUtils.isNotEmpty(codeByUserName)) {
            queryWrapper.in("code", codeByUserName);
        }
        if (!StringUtils.isEmpty(cmd.getKeyWord())) {
            queryWrapper.and(wrapper -> wrapper.like("name", cmd.getKeyWord()).or().like("code", cmd.getKeyWord()));
        }
        queryWrapper.orderByDesc("id");
        return clusterMapper.selectPage(page, queryWrapper);
    }
}
