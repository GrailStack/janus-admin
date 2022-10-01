package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryClusterUserDetailCmd;
import org.xujin.janus.infrastructure.ClusterUserDetailCo;
import org.xujin.janus.domain.user.ports.ClusterUserPort;
import org.xujin.janus.infrastructure.converter.ClusterUserConverter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:56
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryClusterUserDetailCmdExe implements CommandExecutorI<ResultData<ClusterUserDetailCo>, QueryClusterUserDetailCmd> {

    @Resource
    private ClusterUserConverter converter;

    @Resource
    private ClusterUserPort clusterUserPort;

    @Override
    public ResultData<ClusterUserDetailCo> execute(QueryClusterUserDetailCmd cmd) {
        ResultData<ClusterUserDetailCo> resultData = ResultData.success(null);
        check(cmd);
        resultData.setData(converter.entityToCo(clusterUserPort.findById(cmd.getId())));
        return resultData;
    }

    protected boolean check(QueryClusterUserDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"集群id不能为空");
        return true;
    }


}
