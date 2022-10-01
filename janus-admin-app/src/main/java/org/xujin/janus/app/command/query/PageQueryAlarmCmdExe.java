package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PageAlarmCmd;
import org.xujin.janus.app.command.co.AlarmDetailCO;
import org.xujin.janus.app.converter.AlarmClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterConfigMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:43
 * @desc
 */

@Slf4j
@CmdHandler
public class PageQueryAlarmCmdExe implements CommandExecutorI<ResultData<PageResult<AlarmDetailCO>>, PageAlarmCmd> {

    @Autowired
    private AlarmClientConverter converter;

    @Autowired
    private AlarmMapper alarmMapper;

    @Autowired
    private ClusterMapper clusterMapper;

    @Override
    public ResultData<PageResult<AlarmDetailCO>> execute(PageAlarmCmd cmd) {
        ResultData<PageResult<AlarmDetailCO>> resultData = ResultData.success(null);
        check(cmd);
        IPage<AlarmDO> page = page(cmd);
        PageResult<AlarmDetailCO> pageResult = new PageResult<>();
        List<AlarmDetailCO> list = converter.dataToClients(page.getRecords());
        setResultClusterName(list);
        pageResult.setList(list);
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        resultData.setData(pageResult);
        return resultData;
    }

    protected boolean check(PageAlarmCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        return true;
    }

    public IPage<AlarmDO> page(PageAlarmCmd cmd) {
        Page<AlarmDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<AlarmDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        if (!StringUtils.isEmpty(cmd.getClusterCode())) {
            queryWrapper.eq("cluster_code", cmd.getClusterCode());
        }
        if (null!=cmd.getStatus()) {
            queryWrapper.eq("status", cmd.getStatus());
        }
        if (!StringUtils.isEmpty(cmd.getSort())) {
            queryWrapper.orderByDesc(cmd.getSort());
        } else {
            queryWrapper.orderByDesc("id");
        }
        return alarmMapper.selectPage(page, queryWrapper);
    }

    public void setResultClusterName(List<AlarmDetailCO> list) {
        Optional.ofNullable(list).orElse(Arrays.asList())
                .stream().forEach(x->{
            if (null == x) {
                return;
            }
            String clusterCode = x.getClusterCode();
            QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("code", clusterCode);
            ClusterDO clusterDO = clusterMapper.selectOne(queryWrapper);
            if (null != clusterDO) {
                x.setClusterName(clusterDO.getName());
            }
        });
    }
}

