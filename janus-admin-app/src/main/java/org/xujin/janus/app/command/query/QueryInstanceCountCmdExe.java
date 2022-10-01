package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryInstanceCountCmd;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.InstanceMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 14:34
 **/
@CmdHandler
public class QueryInstanceCountCmdExe implements CommandExecutorI<ResultData<Integer>, QueryInstanceCountCmd> {

    @Resource
    private InstanceMapper instanceMapper;

    @Override
    public ResultData<Integer> execute(QueryInstanceCountCmd cmd) {
        Integer result = 0;
        result = countInstance();
        return ResultData.success(result);
    }

    public Integer countInstance() {
        QueryWrapper<InstanceDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        return instanceMapper.selectCount(queryWrapper);
    }


}
