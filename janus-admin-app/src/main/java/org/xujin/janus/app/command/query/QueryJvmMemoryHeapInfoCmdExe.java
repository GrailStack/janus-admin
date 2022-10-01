package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.QueryJvmMemoryHeapInfoCmd;
import org.xujin.janus.infrastructure.tunnel.influxdb.InfluxDBService;

import javax.annotation.Resource;
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
public class QueryJvmMemoryHeapInfoCmdExe implements CommandExecutorI<ResultData<Map<String, Object>>, QueryJvmMemoryHeapInfoCmd> {

    @Resource
    private InfluxDBService influxDBService;

    @Override
    public ResultData<Map<String, Object>> execute(QueryJvmMemoryHeapInfoCmd cmd) {
        ResultData<Map<String, Object>> resultData = ResultData.success(null);
        check(cmd);
        Map<String, Object> queryJVMHeapMemoryCommitted = influxDBService.queryJVMHeapMemoryCommitted("Janus_Test", "192.168.46.222:8081", "heap");
        Map<String, Object> queryJVMHeapMemoryUsed = influxDBService.queryJVMHeapMemoryUsed("Janus_Test", "192.168.46.222:8081", "heap");
        Map<String, Object> queryJVMHeapMemoryMax = influxDBService.queryJVMHeapMemoryMax("Janus_Test", "192.168.46.222:8081", "heap");
        HashMap<String, Object> result = new HashMap<>();
        result.put("Committed", queryJVMHeapMemoryCommitted.get("totalValues"));
        result.put("Used", queryJVMHeapMemoryUsed.get("totalValues"));
        result.put("Max", queryJVMHeapMemoryMax.get("totalValues"));
        resultData.setData(result);
        return resultData;
    }

    protected boolean check(QueryJvmMemoryHeapInfoCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getClusterCode(), "集群编号不能为null");
        return true;
    }

}
