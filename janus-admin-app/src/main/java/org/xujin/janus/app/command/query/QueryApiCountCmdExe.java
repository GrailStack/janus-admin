package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryApiCountCmd;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;

/**
 * Desc:
 *
 * @author chenglong.ren
 * @date 2020/6/11 14:34
 **/
@CmdHandler
public class QueryApiCountCmdExe implements CommandExecutorI<ResultData<Long>, QueryApiCountCmd> {

    @Autowired
    private ApiReleaseMapper apiReleaseMapper;

    @Override
    public ResultData<Long> execute(QueryApiCountCmd cmd) {
        Long result = 0L;
        result = countApi();
        return ResultData.success(result);
    }

    public Long countApi() {
        QueryWrapper<ApiReleaseDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", HaloConstant.API_STATUS_ONLINE);
        return apiReleaseMapper.selectCount(queryWrapper);
    }


}
