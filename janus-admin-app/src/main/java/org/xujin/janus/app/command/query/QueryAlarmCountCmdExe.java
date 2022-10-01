package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryAlarmCountCmd;
import org.xujin.janus.client.cmo.AlarmCmd;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 14:34
 **/
@CmdHandler
public class QueryAlarmCountCmdExe implements CommandExecutorI<ResultData<Integer>, QueryAlarmCountCmd> {

    @Resource
    private AlarmMapper alarmMapper;

    @Override
    public ResultData<Integer> execute(QueryAlarmCountCmd cmd) {
        Integer result = 0;
        result = countCluster();
        return ResultData.success(result);
    }

    public Integer countCluster() {
        QueryWrapper<AlarmDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        return alarmMapper.selectCount(queryWrapper);
    }


}
