package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.UpdateClusterCmd;
import org.xujin.janus.app.converter.ClusterClientConverter;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.service.ClusterService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/15 11:00
 * @desc
 */
@Slf4j
@CmdHandler
public class UpdateClusterCmdExe implements CommandExecutorI<ResultData, UpdateClusterCmd> {

    @Resource
    ClusterClientConverter converter;

    @Override
    public ResultData execute(UpdateClusterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        ClusterEntity clusterEntity = converter.updateCmdToEntity(cmd);
        clusterEntity.update();
        return resultData;
    }

    protected boolean check(UpdateClusterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"集群id不能为空");
        return true;
    }
}
