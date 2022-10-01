package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.DeleteClusterCmd;
import org.xujin.janus.app.converter.ClusterClientConverter;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.infrastructure.converter.ClusterConverter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;


/**
 * @author chenglong.ren
 */
@Slf4j
@CmdHandler
public class DeleteClusterCmdExe implements CommandExecutorI<ResultData, DeleteClusterCmd> {

    @Resource
    ClusterClientConverter converter;

    @Override
    public ResultData execute(DeleteClusterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        ClusterEntity clusterEntity = converter.deleteCmdToEntity(cmd);
        clusterEntity.delete();
        return resultData;
    }

    protected boolean check(DeleteClusterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId() != null, "集群id不能为空");
        return true;
    }
}