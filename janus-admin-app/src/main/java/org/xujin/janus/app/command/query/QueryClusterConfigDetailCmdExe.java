package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryClusterConfigDetailCmd;
import org.xujin.janus.app.command.co.ClusterConfigDetailCO;
import org.xujin.janus.app.converter.ClusterConfigClientConverter;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterConfigMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/15 11:10
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryClusterConfigDetailCmdExe implements CommandExecutorI<ResultData<ClusterConfigDetailCO>, QueryClusterConfigDetailCmd> {

    @Resource
    ClusterConfigMapper clusterConfigMapper;

    @Resource
    ClusterConfigClientConverter converter;

    @Override
    public ResultData<ClusterConfigDetailCO> execute(QueryClusterConfigDetailCmd cmd) {
        ResultData<ClusterConfigDetailCO> resultData = ResultData.success(null);
        check(cmd);
        ClusterConfigDO detail = detail(cmd);
        ClusterConfigDetailCO clusterConfigDetailCO = converter.dataToClient(detail);
        resultData.setData(clusterConfigDetailCO);
        return resultData;
    }

    protected boolean check(QueryClusterConfigDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"id不能为空");
        return true;
    }

    public ClusterConfigDO detail(QueryClusterConfigDetailCmd cmd) {
        return clusterConfigMapper.selectById(cmd.getId());
    }

}
