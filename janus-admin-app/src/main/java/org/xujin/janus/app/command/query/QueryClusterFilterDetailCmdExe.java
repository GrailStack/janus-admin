package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryClusterFilterDetailCmd;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterFilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterFilterDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:56
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryClusterFilterDetailCmdExe implements CommandExecutorI<ResultData<List<FilterDetailCo>>, QueryClusterFilterDetailCmd> {

    @Resource
    private FilterMapper filterMapper;

    @Resource
    private FilterClientConverter converter;

    @Resource
    private ClusterFilterMapper clusterFilterMapper;

    @Override
    public ResultData<List<FilterDetailCo>> execute(QueryClusterFilterDetailCmd cmd) {
        ResultData<List<FilterDetailCo>> resultData = ResultData.success(null);
        check(cmd);
        List<Integer> filtersByClusterCode = findFiltersByClusterCode(cmd.getClusterCode());
        if (CollectionUtils.isNotEmpty(filtersByClusterCode)) {
            resultData.setData(converter.dataToClients(selectBatch(filtersByClusterCode)));
        }
        return resultData;
    }

    protected boolean check(QueryClusterFilterDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getClusterCode()!=null,"集群code不能为空");
        return true;
    }

    public List<FilterDO> selectBatch(List<Integer> filterIds) {
        return filterMapper.selectBatchIds(filterIds);
    }

    public List<Integer> findFiltersByClusterCode(String clusterCode) {
        QueryWrapper<ClusterFilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        queryWrapper.eq("cluster_code", clusterCode);
        List<ClusterFilterDO> clusterFilterDOS = clusterFilterMapper.selectList(queryWrapper);
        return Optional.ofNullable(clusterFilterDOS).orElse(Arrays.asList())
                .stream()
                .map(ClusterFilterDO::getFilterId)
                .collect(Collectors.toList());
    }

}
