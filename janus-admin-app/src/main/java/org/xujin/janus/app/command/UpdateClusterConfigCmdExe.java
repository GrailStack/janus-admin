package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.UpdateClusterConfigCmd;
import org.xujin.janus.app.converter.ClusterConfigClientConverter;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.entity.ClusterConfigE;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterConfigMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:22
 * @desc
 */
@Slf4j
@CmdHandler
public class UpdateClusterConfigCmdExe implements CommandExecutorI<ResultData, UpdateClusterConfigCmd> {
    @Resource
    private ClusterConfigClientConverter clientConverter;
    @Resource
    private ClusterConfigMapper clusterConfigMapper;

    @Override
    public ResultData execute(UpdateClusterConfigCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        ClusterConfigDO clusterConfigDO = clusterConfigMapper.selectById(cmd.getId());
        if (null == clusterConfigDO) {
            throw new BusinessException("400", "没有该集群的相关配置");
        }
        ClusterConfigE clusterConfigE = clientConverter.updateCmdToEntity(cmd);
        clusterConfigE.setModifier(SessionUtils.getUserName());
        clusterConfigE.update();
        return resultData;
    }

    protected boolean check(UpdateClusterConfigCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"id不能为空");
        return true;
    }
}
