package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PageClusterConfigCmd;
import org.xujin.janus.app.command.co.ClusterConfigDetailCO;
import org.xujin.janus.app.converter.ClusterConfigClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApplyMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterConfigMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:43
 * @desc
 */

@Slf4j
@CmdHandler
public class PageQueryClusterConfigCmdExe implements CommandExecutorI<ResultData<PageResult<ClusterConfigDetailCO>>, PageClusterConfigCmd> {

    @Autowired
    private ClusterConfigClientConverter clusterConfigClientConverter;

    @Autowired
    private ClusterConfigMapper clusterConfigMapper;

    @Override
    public ResultData<PageResult<ClusterConfigDetailCO>> execute(PageClusterConfigCmd cmd) {
        ResultData<PageResult<ClusterConfigDetailCO>> resultData = ResultData.success(null);
        check(cmd);
        IPage<ClusterConfigDO> page = page(cmd);
        PageResult<ClusterConfigDetailCO> pageResult = new PageResult<>();
        pageResult.setList(clusterConfigClientConverter.dataToClients(page.getRecords()));
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        resultData.setData(pageResult);
        return resultData;
    }

    protected boolean check(PageClusterConfigCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        return true;
    }

    public IPage<ClusterConfigDO> page(PageClusterConfigCmd cmd) {
        Page<ClusterConfigDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<ClusterConfigDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        if (!StringUtils.isEmpty(cmd.getClusterCode())) {
            queryWrapper.eq("cluster_code", cmd.getClusterCode());
        }

        if (!StringUtils.isEmpty(cmd.getConfigKey())) {
            queryWrapper.eq("config_key", cmd.getConfigKey());
        }
        queryWrapper.orderByDesc("id");
        return clusterConfigMapper.selectPage(page, queryWrapper);
    }
}

