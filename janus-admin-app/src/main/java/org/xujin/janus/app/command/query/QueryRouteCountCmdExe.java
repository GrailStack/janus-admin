package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryRouteCountCmdCmd;
import org.xujin.janus.infrastructure.tunnel.influxdb.InfluxDBService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @author chenglong.ren
 * @date 2019/7/17 17:17
 **/
@Slf4j
@CmdHandler
public class QueryRouteCountCmdExe implements CommandExecutorI<ResultData<Map<String, Object>>, QueryRouteCountCmdCmd> {

    @Resource
    private InfluxDBService influxDBService;

    @Override
    public ResultData<Map<String, Object>> execute(QueryRouteCountCmdCmd cmd) {
        ResultData<Map<String, Object>> resultData = ResultData.success(null);
        check(cmd);
        Map<String, Object> requestReceive = influxDBService.queryRouteCount(cmd.getClusterCode(), cmd.getHost());
        Map<String, Object> map = new HashMap<>();
        map.put(cmd.getHost(), requestReceive.get("total") == null ? Arrays.asList() : requestReceive.get("total"));
        resultData.setData(map);
        return resultData;
    }
    protected boolean check(QueryRouteCountCmdCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getClusterCode(), "集群编号不能为null");
        return true;
    }

}
