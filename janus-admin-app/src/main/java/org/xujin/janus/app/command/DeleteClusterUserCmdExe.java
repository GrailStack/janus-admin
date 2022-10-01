package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.DeleteClusterUserCmd;
import org.xujin.janus.app.converter.ClusterUserClientConverter;
import org.xujin.janus.domain.user.entity.ClusterUserE;
import org.xujin.janus.domain.user.ports.ClusterUserPort;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:18
 * @desc
 */
@CmdHandler
public class DeleteClusterUserCmdExe implements CommandExecutorI<ResultData, DeleteClusterUserCmd> {

    @Resource
    private ClusterUserClientConverter converter;

    @Resource
    private ClusterUserPort clusterUserPort;

    private final Byte delete = 1;

    @Override
    public ResultData execute(DeleteClusterUserCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        ClusterUserE findById = clusterUserPort.findById(cmd.getId());
        if (null == findById) {
            throw new BusinessException("400", "关系不存在");
        }
        ClusterUserE clusterUserE = converter.deleteCmdToEntity(cmd);
        clusterUserE.setIsDeleted(delete);
        clusterUserE.delete();
        return resultData;
    }


    protected boolean check(DeleteClusterUserCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId() != null, "id不能为空");
        return true;
    }
}
