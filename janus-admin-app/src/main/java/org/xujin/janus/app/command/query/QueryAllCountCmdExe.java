package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryAllCountCmd;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.InstanceMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 14:34
 **/
@CmdHandler
public class QueryAllCountCmdExe implements CommandExecutorI<ResultData<Map<String,Integer>>, QueryAllCountCmd> {

    @Resource
    private AlarmMapper alarmMapper;

    @Autowired
    private ApiReleaseMapper apiReleaseMapper;

    @Resource
    private InstanceMapper instanceMapper;

    @Resource
    private ClusterMapper clusterMapper;

    @Override
    public ResultData<Map<String,Integer>> execute(QueryAllCountCmd cmd) {
        ResultData<Map<String,Integer>> resultData = ResultData.success(null);
        Map<String, Integer> map = new HashMap<>();
        Integer countAlarm = countAlarm();
        Integer countApi = countApi();
        Integer countInstance = countInstance();
        Integer countCluster = countCluster();
        map.put("countAlarm", countAlarm);
        map.put("countApi", countApi);
        map.put("countInstance",countInstance );
        map.put("countCluster", countCluster);
        resultData.setData(map);
        return resultData;
    }

    public Integer countAlarm() {
        QueryWrapper<AlarmDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        return alarmMapper.selectCount(queryWrapper);
    }

    public Integer countApi() {
        QueryWrapper<ApiReleaseDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", HaloConstant.API_STATUS_ONLINE);
        return apiReleaseMapper.selectCount(queryWrapper);
    }

    public Integer countInstance() {
        QueryWrapper<InstanceDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        return instanceMapper.selectCount(queryWrapper);
    }
    public Integer countCluster() {
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        return clusterMapper.selectCount(queryWrapper);
    }

}
