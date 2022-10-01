package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddClusterUserCmd;
import org.xujin.janus.app.converter.ClusterUserClientConverter;
import org.xujin.janus.domain.user.entity.ClusterUserE;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:18
 * @desc
 */
@CmdHandler
public class AddClusterUserCmdExe implements CommandExecutorI<ResultData, AddClusterUserCmd> {

    @Resource
    private ClusterUserClientConverter converter;

    @Override
    public ResultData execute(AddClusterUserCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        ClusterUserE clusterUserE = converter.addCmdToEntity(cmd);
        clusterUserE.save();
        return resultData;
    }


    protected boolean check(AddClusterUserCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        return true;
    }
}
