package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryClusterCountCmd;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 14:34
 **/
@CmdHandler
public class QueryClusterCountCmdExe implements CommandExecutorI<ResultData<Long>, QueryClusterCountCmd> {

    @Resource
    private ClusterMapper clusterMapper;

    @Override
    public ResultData<Long> execute(QueryClusterCountCmd cmd) {
        Long result = 0L;
        result = countCluster();
        return ResultData.success(result);
    }

    public Long countCluster() {
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        return clusterMapper.selectCount(queryWrapper);
    }


}
