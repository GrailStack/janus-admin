package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryFilterDetailCmd;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:56
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryFilterDetailCmdExe implements CommandExecutorI<ResultData<FilterDetailCo>, QueryFilterDetailCmd> {

    @Resource
    private FilterMapper filterMapper;

    @Resource
    private FilterClientConverter converter;

    @Override
    public ResultData<FilterDetailCo> execute(QueryFilterDetailCmd cmd) {
        ResultData<FilterDetailCo> resultData = ResultData.success(null);
        check(cmd);
        resultData.setData(converter.dataToClient(detail(cmd)));
        return resultData;
    }

    protected boolean check(QueryFilterDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"集群id不能为空");
        return true;
    }

    public FilterDO detail(QueryFilterDetailCmd cmd) {
        return filterMapper.selectById(cmd.getId());
    }

}
