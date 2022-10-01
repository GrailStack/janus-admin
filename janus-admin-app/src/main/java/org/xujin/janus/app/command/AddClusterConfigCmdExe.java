package org.xujin.janus.app.command;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.AddClusterConfigCmd;
import org.xujin.janus.app.converter.ClusterConfigClientConverter;
import org.xujin.janus.domain.user.entity.ClusterConfigE;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.converter.ClusterConfigConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterConfigMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/27 10:02
 * @desc
 */
@CmdHandler
public class AddClusterConfigCmdExe implements CommandExecutorI<ResultData, AddClusterConfigCmd> {

    @Autowired
    private ClusterConfigClientConverter clusterConfigConverter;

    @Autowired
    private ClusterConfigMapper clusterConfigMapper;

    @Override
    public ResultData execute(AddClusterConfigCmd cmd) {
        ResultData resultData = ResultData.success(null);
        ClusterConfigE clusterConfigE = clusterConfigConverter.addCmdToEntity(cmd);
        clusterConfigE.setCreator(SessionUtils.getUserName());
        QueryWrapper<ClusterConfigDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_key", cmd.getConfigKey());
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        List<ClusterConfigDO> clusterConfigDOS = clusterConfigMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(clusterConfigDOS)) {
            throw new BusinessException("400", "configKey不能重复");
        }
        clusterConfigE.save();
        return resultData;
    }


    protected boolean check(AddClusterConfigCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getClusterCode(), "集群编码不能为null");
        Preconditions.checkNotNull(cmd.getConfigKey(), "配置key不能为null");
        Preconditions.checkNotNull(cmd.getConfigValue(), "配置value不能为null");
        return true;
    }
}
