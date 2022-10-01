package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryDiskFreeCmdCmd;
import org.xujin.janus.infrastructure.tunnel.influxdb.InfluxDBService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Desc:
 *
 * @author chenglong.ren
 * @date 2019/7/17 17:17
 **/
@Slf4j
@CmdHandler
public class QueryDiskFreeCmdExe implements CommandExecutorI<ResultData<Map<String, Object>>, QueryDiskFreeCmdCmd> {

    @Resource
    private InfluxDBService influxDBService;

    @Override
    public ResultData<Map<String, Object>> execute(QueryDiskFreeCmdCmd cmd) {
        ResultData<Map<String, Object>> resultData = ResultData.success(null);
        check(cmd);
        Map<String, Object> map = influxDBService.queryDiskInfo(cmd.getClusterCode(), cmd.getIp());
        resultData.setData(map);
        return resultData;
    }
    protected boolean check(QueryDiskFreeCmdCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getClusterCode(), "集群编号不能为null");
        return true;
    }

}
