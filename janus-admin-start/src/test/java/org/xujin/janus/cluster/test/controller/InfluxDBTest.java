package org.xujin.janus.cluster.test.controller;

import org.junit.Test;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.QueryJvmMemoryHeapInfoCmd;
import org.xujin.janus.app.command.cmo.query.*;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.InfluxDBController;
import org.xujin.janus.infrastructure.tunnel.influxdb.InfluxDBService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:38
 * @desc
 */
public class InfluxDBTest extends CommonTest {
    @Resource
    private InfluxDBController influxDBController;

    @Resource
    private InfluxDBService influxDBService;

    @Test
    public void testQuery() {
        QueryQpsCmd queryQpsCmd = new QueryQpsCmd();
        queryQpsCmd.setClusterCode("Janus_Test");
        ResultData<Map<String, Object>> mapResultData = influxDBController.searchQPS(queryQpsCmd);
        System.out.println(mapResultData);
    }

    @Test
    public void testCountAll() {
        ResultData<Map<String, Integer>> allNums = influxDBController.getAllNums();
        System.out.println(allNums);
    }

    @Test
    public void testQueryRouteCount() {
        QueryRouteCountCmdCmd queryQpsCmd = new QueryRouteCountCmdCmd();
        queryQpsCmd.setClusterCode("Janus_Test");
        queryQpsCmd.setHost("192.168.46.222:8081");
        ResultData<Map<String, Object>> mapResultData = influxDBController.searchRouteCount(queryQpsCmd);
        System.out.println(mapResultData);
    }

    @Test
    public void testQueryRequestError() {
        QueryRequestErrorCmdCmd queryQpsCmd = new QueryRequestErrorCmdCmd();
        queryQpsCmd.setClusterCode("Janus_Test");
        queryQpsCmd.setHost("192.168.47.15:8081");
        ResultData<Map<String, Object>> mapResultData = influxDBController.searchRequestError(queryQpsCmd);
        System.out.println(mapResultData);
    }

    @Test
    public void testDiskFree() {
        QueryDiskFreeCmdCmd cmd = new QueryDiskFreeCmdCmd();
        cmd.setClusterCode("Janus_Test");
        cmd.setIp("192.168.46.246:8081");
        ResultData<Map<String, Object>> mapResultData = influxDBController.searchDiskInfo(cmd);
        System.out.println(mapResultData);
    }


    @Test
    public void testJvmMemory() {
        QueryJvmMemoryUsedCmd cmd = new QueryJvmMemoryUsedCmd();
        cmd.setClusterCode("Janus_Test");
        cmd.setHost("192.168.46.222:8081");
        ResultData<Map<String, Object>> mapResultData = influxDBController.searchJvmMemory(cmd);
        System.out.println(mapResultData);
    }

    @Test
    public void testJVMHeap() {
        Map<String, Object> queryJVMHeapMemoryCommitted = influxDBService.queryJVMHeapMemoryCommitted("Janus_Test", "192.168.46.222:8081", "heap");
        Map<String, Object> queryJVMHeapMemoryUsed = influxDBService.queryJVMHeapMemoryUsed("Janus_Test", "192.168.46.222:8081", "heap");
        Map<String, Object> queryJVMHeapMemoryMax = influxDBService.queryJVMHeapMemoryMax("Janus_Test", "192.168.46.222:8081", "heap");
        HashMap<String, Object> result = new HashMap<>();
        result.put("Committed", queryJVMHeapMemoryCommitted.get("totalValues"));
        result.put("Used", queryJVMHeapMemoryUsed.get("totalValues"));
        result.put("Max", queryJVMHeapMemoryMax.get("totalValues"));
        System.out.println(result);
    }

    @Test
    public void testJvmHeapInfo() {
        QueryJvmMemoryHeapInfoCmd cmd = new QueryJvmMemoryHeapInfoCmd();
        cmd.setHost("192.168.46.222:8081");
        cmd.setClusterCode("Janus_Test");
        cmd.setArea("heap");
        ResultData<Map<String, Object>> mapResultData = influxDBController.searchJvmHeapInfo(cmd);
        System.out.println(mapResultData);
    }

    @Test
    public void testDiskTotal() {
        Map<String, Object> diskFree = influxDBService.queryDiskFree("Janus_Tes");
        Map<String, Object> diskTotal = influxDBService.queryDiskTotal("Janus_Tes");
        Map<String, Object> processFilesMax = influxDBService.queryProcessFilesMax("Janus_Test");
        Map<String, Object> processFilesOpen = influxDBService.queryProcessFilesOpen("Janus_Test");
        Map<String, Object> systemCpuCount = influxDBService.querySystemCpuCount("Janus_Tes");
        Map<String, Object> systemCpuUsage = influxDBService.querySystemCpuUsage("Janus_Tes");
        Map<String, Object> breakerFailRate = influxDBService.queryBreakerFailRate("user");
        Map<String, Object> breakerSlowRate = influxDBService.queryBreakerSlowRate("user");
        Map<String, Object> concurrentConnections = influxDBService.queryConcurrentConnections("user");
        Map<String, Object> connections = influxDBService.queryConnections("user");




    }
}
