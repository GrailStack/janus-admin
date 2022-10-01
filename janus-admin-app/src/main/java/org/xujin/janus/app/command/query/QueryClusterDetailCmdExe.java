package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryClusterDetailCmd;
import org.xujin.janus.app.converter.ClusterClientConverter;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/15 11:10
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryClusterDetailCmdExe implements CommandExecutorI<ResultData<ClusterDetailCo>, QueryClusterDetailCmd> {

    @Resource
    ClusterMapper clusterMapper;

    @Resource
    ClusterClientConverter converter;

    @Override
    public ResultData<ClusterDetailCo> execute(QueryClusterDetailCmd cmd) {
        ResultData<ClusterDetailCo> resultData = ResultData.success(null);
        check(cmd);
        ClusterDO detail = detail(cmd);
        ClusterDetailCo clusterDetailCo = converter.dataToClient(detail);
        resultData.setData(clusterDetailCo);
        return resultData;
    }

    protected boolean check(QueryClusterDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"集群id不能为空");
        return true;
    }

    public ClusterDO detail(QueryClusterDetailCmd cmd) {
        return clusterMapper.selectById(cmd.getId());
    }

}
