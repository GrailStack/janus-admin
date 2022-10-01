package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.UpdateClusterFilterCmd;
import org.xujin.janus.app.converter.ClusterClientConverter;
import org.xujin.janus.domain.user.entity.ClusterFilterE;
import org.xujin.janus.domain.user.service.FilterService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/6/3 9:54
 * @desc
 */
@CmdHandler
public class UpdateClusterFilterCmdExe implements CommandExecutorI<ResultData, UpdateClusterFilterCmd> {

    @Resource
    private ClusterClientConverter clusterClientConverter;

    @Resource
    private FilterService filterService;

    @Override
    public ResultData execute(UpdateClusterFilterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        String clusterCode = cmd.getCode();
        filterService.deleteClusterFilter(clusterCode);
        List<ClusterFilterE> list = clusterClientConverter.addToEntities(cmd);
        filterService.addClusterFilter(list);
        return resultData;
    }

    protected boolean check(UpdateClusterFilterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getCode(), "clusterCode不能为null");
        return true;
    }
}
