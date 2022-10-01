package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryAllFilterCmd;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:56
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryAllFilterCmdExe implements CommandExecutorI<ResultData<List<FilterDetailCo>>, QueryAllFilterCmd> {

    @Resource
    private FilterMapper filterMapper;

    @Resource
    private FilterClientConverter converter;

    @Override
    public ResultData<List<FilterDetailCo>> execute(QueryAllFilterCmd cmd) {
        ResultData<List<FilterDetailCo>> resultData = ResultData.success(null);
        resultData.setData(converter.dataToClients(listFilters()));
        return resultData;
    }

    public List<FilterDO> listFilters() {
        QueryWrapper<FilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        return filterMapper.selectList(queryWrapper);
    }

}
