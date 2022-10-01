package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PageQueryClusterUserCmd;
import org.xujin.janus.app.converter.ClusterUserClientConverter;
import org.xujin.janus.infrastructure.ClusterUserDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterUserDO;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 */
@CmdHandler
@Slf4j
public class PageQueryClusterUserCmdExe implements CommandExecutorI<ResultData<PageResult<ClusterUserDetailCo>>, PageQueryClusterUserCmd> {

    @Resource
    private ClusterUserMapper clusterUserMapper;

    @Resource
    private ClusterUserClientConverter clusterUserClientConverter;

    @Override
    public ResultData<PageResult<ClusterUserDetailCo>> execute(PageQueryClusterUserCmd pageQueryUserCmd) {
        ResultData<PageResult<ClusterUserDetailCo>> resultData = ResultData.success(null);
        check(pageQueryUserCmd);
        IPage<ClusterUserDO> page = page(pageQueryUserCmd);
        PageResult<ClusterUserDetailCo> pageResult = new PageResult<>();
        pageResult.setList(clusterUserClientConverter.dataToClients(page.getRecords()));
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        resultData.setData(pageResult);
        return resultData;
    }


    protected void check(PageQueryClusterUserCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getPageNo(), "pageNo不能为null");
        Preconditions.checkNotNull(cmd.getPageSize(), "pageSize不能为null");
        Preconditions.checkArgument(cmd.getPageNo() >= 1, "pageNo不能小于1");
        Preconditions.checkArgument(cmd.getPageSize() >= 1, "pageSize不能小于1");
        Preconditions.checkArgument(cmd.getPageSize() <= 500, "pageSize不能大于500");
    }

    public IPage<ClusterUserDO> page(PageQueryClusterUserCmd cmd) {
        Page<ClusterUserDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<ClusterUserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", "0");
        if (StringUtils.isNotBlank(cmd.getUserName())) {
            wrapper.eq("user_name", cmd.getUserName());
        }
        if (StringUtils.isNotBlank(cmd.getClusterCode())) {
            wrapper.eq("cluster_code", cmd.getClusterCode());
        }
        wrapper.orderByDesc("id");
        return clusterUserMapper.selectPage(page, wrapper);
    }
}
