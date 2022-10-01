package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryRequestErrorCmdCmd;
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
public class QueryRequestErrorCmdExe implements CommandExecutorI<ResultData<Map<String, Object>>, QueryRequestErrorCmdCmd> {

    @Resource
    private InfluxDBService influxDBService;

    @Override
    public ResultData<Map<String, Object>> execute(QueryRequestErrorCmdCmd cmd) {
        ResultData<Map<String, Object>> resultData = ResultData.success(null);
        Map<String, Object> requestReceive = influxDBService.queryRequestError(cmd.getClusterCode(), cmd.getHost());
        Map<String, Object> qpsReceive = influxDBService.queryRequestReceive(cmd.getClusterCode(), cmd.getHost());
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put(cmd.getHost(), requestReceive.get(cmd.getHost()) == null ? Arrays.asList() : requestReceive.get(cmd.getHost()));

        Map<String, Object> QPSMap = new HashMap<>();
        QPSMap.put(cmd.getHost(), qpsReceive.get(cmd.getHost()) == null ? Arrays.asList() : qpsReceive.get(cmd.getHost()));

        Map<String, Object> result = new HashMap<>();
        result.put("qps", QPSMap);
        result.put("error", errorMap);
        resultData.setData(result);
        return resultData;
    }
    protected boolean check(QueryRequestErrorCmdCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getClusterCode(), "集群编号不能为null");
        return true;
    }

}
