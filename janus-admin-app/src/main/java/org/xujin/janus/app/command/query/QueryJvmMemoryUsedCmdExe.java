package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryJvmMemoryUsedCmd;
import org.xujin.janus.infrastructure.tunnel.db.influxdb.KeyValueCO;
import org.xujin.janus.infrastructure.tunnel.influxdb.InfluxDBService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author chenglong.ren
 * @date 2019/7/17 17:17
 **/
@Slf4j
@CmdHandler
public class QueryJvmMemoryUsedCmdExe implements CommandExecutorI<ResultData<Map<String, Object>>, QueryJvmMemoryUsedCmd> {

    @Resource
    private InfluxDBService influxDBService;

    @Override
    public ResultData<Map<String, Object>> execute(QueryJvmMemoryUsedCmd cmd) {
        ResultData<Map<String, Object>> resultData = ResultData.success(null);
        check(cmd);
        Map<String, Object> queryJvmMemoryUsed = influxDBService.queryJvmMemoryUsed(cmd.getClusterCode(), cmd.getHost());
        Map<String, Object> queryJvmMemoryMax = influxDBService.queryJvmMemoryMax(cmd.getClusterCode(), cmd.getHost());
        Map<String, Object> queryJvmMemoryCommitted = influxDBService.queryJvmMemoryCommitted(cmd.getClusterCode(), cmd.getHost());
        List<KeyValueCO> memoryUsed = queryJvmMemoryUsed.get(cmd.getHost()) == null ? Arrays.asList() : (List<KeyValueCO>) queryJvmMemoryUsed.get(cmd.getHost());
        List<KeyValueCO> memoryMax = queryJvmMemoryMax.get(cmd.getHost()) == null ? Arrays.asList() : (List<KeyValueCO>) queryJvmMemoryMax.get(cmd.getHost());
        List<KeyValueCO> memoryCommitted = queryJvmMemoryCommitted.get(cmd.getHost()) == null ? Arrays.asList() : (List<KeyValueCO>) queryJvmMemoryCommitted.get(cmd.getHost());
        Map<String, Object> map = new HashMap<>();
        KeyValueCO keyValueCO = new KeyValueCO();
        map.put("memoryUsed", memoryUsed.size() > 0 ? memoryUsed.get(0) : keyValueCO);
        map.put("memoryMax", memoryMax.size() > 0 ? memoryMax.get(0) : keyValueCO);
        map.put("memoryCommitted", memoryCommitted.size() > 0 ? memoryCommitted.get(0) : keyValueCO);
        resultData.setData(map);
        return resultData;
    }

    protected boolean check(QueryJvmMemoryUsedCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getClusterCode(), "集群编号不能为null");
        return true;
    }

}
