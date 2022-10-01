package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.query.ListApiFilterCmd;
import org.xujin.janus.app.command.co.ApiFilterDetailCO;
import org.xujin.janus.app.command.co.ApiReleaseCO;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.app.converter.ApiFilterClientConverter;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiFilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterFilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiFilterDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterFilterDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author chenglong.ren
 * @date 2020/4/15 11:10
 * @desc
 */
@Slf4j
@CmdHandler
public class ListApiFilterCmdExe implements CommandExecutorI<ResultData<Map<String,Object>>, ListApiFilterCmd> {

    @Resource
    ApiFilterMapper apiFilterMapper;

    @Resource
    ApiFilterClientConverter converter;

    @Autowired
    private ApiReleaseMapper apiReleaseMapper;

    @Autowired
    private ClusterFilterMapper clusterFilterMapper;

    @Autowired
    private FilterMapper filterMapper;

    @Autowired
    private FilterClientConverter filterClientConverter;



    @Override
    public ResultData<Map<String,Object>> execute(ListApiFilterCmd cmd) {
        Map<String, Object> result = new HashMap<>();
        ResultData<Map<String,Object>> resultData = ResultData.success(null);
        check(cmd);
        List<ApiFilterDO> detail = detail(cmd);
        List<ApiFilterDetailCO> apiFilterDetailCOs = converter.dataToClients(detail);
        ApiReleaseDO apiReleaseDO = apiReleaseMapper.selectById(cmd.getApiId());
        ApiReleaseCO apiReleaseCO = BeanMapper.map(apiReleaseDO, ApiReleaseCO.class);
        List<FilterDetailCo> filterDetailCos = new ArrayList<>();
        if (null != apiReleaseDO) {
            String clusterCode = apiReleaseDO.getClusterCode();
            if (!StringUtils.isEmpty(clusterCode)) {
                filterDetailCos = listByClusterCode(clusterCode);
            }
        }
        result.put("apiInfo", apiReleaseCO);
        result.put("filters", apiFilterDetailCOs);
        result.put("clusterFilters", filterDetailCos);
        resultData.setData(result);
        return resultData;
    }

    protected boolean check(ListApiFilterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getApiId()!=null,"id不能为空");
        return true;
    }

    public List<ApiFilterDO> detail(ListApiFilterCmd cmd) {
        QueryWrapper<ApiFilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_id", cmd.getApiId());
        return apiFilterMapper.selectList(queryWrapper);
    }

    public List<FilterDetailCo> listByClusterCode(String clusterCode) {
        List<FilterDetailCo> result = new ArrayList<>();
        QueryWrapper<ClusterFilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        queryWrapper.eq("cluster_code", clusterCode);
        List<ClusterFilterDO> clusterFilterDOS = clusterFilterMapper.selectList(queryWrapper);
        List<Integer> filterIds = Optional.ofNullable(clusterFilterDOS).orElse(Arrays.asList())
                .stream()
                .map(ClusterFilterDO::getFilterId)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(filterIds)) {
            List<FilterDO> filterDOS = filterMapper.selectBatchIds(filterIds);
            result= filterClientConverter.dataToClients(filterDOS);
        }
        return result;

    }
}
