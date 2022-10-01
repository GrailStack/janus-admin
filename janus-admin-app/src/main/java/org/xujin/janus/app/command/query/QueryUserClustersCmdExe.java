package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.query.QueryUserClustersCmd;
import org.xujin.janus.app.converter.ClusterClientConverter;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.domain.user.service.UserService;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 14:34
 **/
@CmdHandler
public class QueryUserClustersCmdExe implements CommandExecutorI<ResultData<List<ClusterDetailCo>>, QueryUserClustersCmd> {

    @Autowired
    private UserService userService;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private ClusterMapper clusterMapper;

    @Resource
    private ClusterClientConverter converter;

    @Override
    public ResultData<List<ClusterDetailCo>> execute(QueryUserClustersCmd cmd) {
        String userName = SessionUtils.getUserName();
        if (StringUtils.isBlank(userName)) {
            throw new BusinessException("400", "无法获取当前用户");
        }
        List<ClusterDetailCo> coList = queryUserClusters(userName);
        return ResultData.success(coList);
    }

    private List<ClusterDetailCo> queryUserClusters(String userName) {
        boolean isAdmin = userService.isAdmin(userName);
        List<ClusterDO> doList = isAdmin ? queryForAdmin() : queryForUser(userName);
        return converter.dataToClients(doList);
    }

    private List<ClusterDO> queryForAdmin() {
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByDesc("id");
        return clusterMapper.selectList(queryWrapper);
    }

    private List<ClusterDO> queryForUser(String userName) {
        List<String> clusterCodes = clusterService.findUserClusters(userName);
        if (CollectionUtils.isEmpty(clusterCodes)) {
            return Lists.newArrayList();
        }
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("code", clusterCodes);
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByDesc("id");
        return clusterMapper.selectList(queryWrapper);
    }

}
