package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryAllFiltersCmd;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.domain.user.ports.ClusterFilterPort;
import org.xujin.janus.domain.user.ports.ClusterUserPort;
import org.xujin.janus.domain.user.ports.UserRolePort;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/15 13:48
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryAllFiltersCmdExe implements CommandExecutorI<ResultData<List<FilterDetailCo>>, QueryAllFiltersCmd> {

    @Resource
    FilterMapper filterMapper;

    @Resource
    FilterClientConverter converter;


    @Override
    public ResultData<List<FilterDetailCo>> execute(QueryAllFiltersCmd cmd) {
        ResultData<List<FilterDetailCo>> resultData = ResultData.success(null);
        check(cmd);
        List<FilterDetailCo> filterDetailCos = new ArrayList<>();
        filterDetailCos = listAllByClassCode(cmd.getClassCode());
        resultData.setData(filterDetailCos);
        return resultData;
    }

    protected boolean check(QueryAllFiltersCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getClassCode(), "clusterCode不能为null");
        return true;
    }

    public List<FilterDetailCo> listAllByClassCode(String classCode) {
        QueryWrapper<FilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_code", classCode);
        return converter.dataToClients(filterMapper.selectList(queryWrapper));
    }


}
