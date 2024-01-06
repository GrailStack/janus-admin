package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PageInstanceCmd;
import org.xujin.janus.app.command.co.InstanceDetailCO;
import org.xujin.janus.app.converter.InstanceClientConverter;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.InstanceMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:43
 * @desc
 */

@Slf4j
@CmdHandler
public class PageQueryInstanceCmdExe implements CommandExecutorI<ResultData<PageResult<InstanceDetailCO>>, PageInstanceCmd> {

    @Autowired
    private InstanceClientConverter converter;

    @Autowired
    private InstanceMapper instanceMapper;

    @Autowired
    private ClusterMapper clusterMapper;

    @Autowired
    private AlarmMapper alarmMapper;


    @Override
    public ResultData<PageResult<InstanceDetailCO>> execute(PageInstanceCmd cmd) {
        ResultData<PageResult<InstanceDetailCO>> resultData = ResultData.success(null);
        check(cmd);
        IPage<InstanceDO> page = page(cmd);
        PageResult<InstanceDetailCO> pageResult = new PageResult<>();
        List<InstanceDetailCO> list = converter.dataToClients(page.getRecords());
        addInfo(list);
        pageResult.setList(list);
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        resultData.setData(pageResult);
        return resultData;
    }

    protected boolean check(PageInstanceCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        return true;
    }

    public IPage<InstanceDO> page(PageInstanceCmd cmd) {
        Page<InstanceDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<InstanceDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        if (!StringUtils.isEmpty(cmd.getClusterCode())) {
            queryWrapper.eq("cluster_code", cmd.getClusterCode());
        }
        if (null!=cmd.getStatus()) {
            queryWrapper.eq("status", cmd.getStatus());
        }
        queryWrapper.orderByDesc("id");
        return instanceMapper.selectPage(page, queryWrapper);
    }

    public void addInfo(List<InstanceDetailCO> list) {
        //补充集群名称   服务owner   告警数量
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(x->{
                String clusterCode = x.getClusterCode();
                QueryWrapper<AlarmDO> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("cluster_code", clusterCode);
                queryWrapper.eq("status", HaloConstant.IS_DELETED_FALSE);
                queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
                Long integer = alarmMapper.selectCount(queryWrapper);
                x.setAlarmCount(integer == null ? 0 : integer);

                QueryWrapper<ClusterDO> queryWrapper1 = new QueryWrapper<>();
                queryWrapper.eq("code", clusterCode);
                queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
                List<ClusterDO> clusterDOS = clusterMapper.selectList(queryWrapper1);
                if (!CollectionUtils.isEmpty(clusterDOS)) {
                    ClusterDO clusterDO = clusterDOS.get(0);
                    x.setClusterName(clusterDO.getName());
                    x.setOwnerName(clusterDO.getOwnerName());
                }

            });
        }
    }
}

