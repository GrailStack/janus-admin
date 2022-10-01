package org.xujin.janus.app.command.query;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryClusterInstancesCmd;
import org.xujin.janus.app.command.co.JanusInstanceCO;
import org.xujin.janus.app.service.ClusterAppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/15 16:44
 **/
@CmdHandler
public class QueryClusterInstancesCmdExe implements CommandExecutorI<ResultData<List<JanusInstanceCO>>, QueryClusterInstancesCmd> {

    @Autowired
    private ClusterAppService clusterAppService;

    @Override
    public ResultData<List<JanusInstanceCO>> execute(QueryClusterInstancesCmd cmd) {
        return ResultData.success(clusterAppService.findClusterInstances(cmd.getClusterCode()));
    }

}
