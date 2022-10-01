package org.xujin.janus.app.command;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.converter.ClusterClientConverter;
import org.xujin.janus.app.command.cmo.AddClusterCmd;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:48
 * @desc
 */
@Slf4j
@CmdHandler
public class AddClusterCmdExe implements CommandExecutorI<ResultData, AddClusterCmd> {

    @Resource
    ClusterClientConverter converter;
    @Resource
    private ClusterMapper clusterMapper;


    @Override
    public ResultData execute(AddClusterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        QueryWrapper<ClusterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", cmd.getCode());
        queryWrapper.eq("is_deleted", "0");
        ClusterDO clusterDO = clusterMapper.selectOne(queryWrapper);
        if (null != clusterDO) {
            throw new BusinessException("400", "集群编码已存在");
        }

        ClusterEntity clusterEntity = converter.addCmdToEntity(cmd);
        clusterEntity.setCreated(cmd.getCurrentUserId());
        clusterEntity.save();
        return resultData;
    }

    protected boolean check(AddClusterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getCode(), "cmd不能为null");
        return true;
    }
}
