package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryApplyDetailCmd;
import org.xujin.janus.app.command.co.ApplyDetailCO;
import org.xujin.janus.app.converter.ApplyClientConverter;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApplyMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/15 11:10
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryApplyDetailCmdExe implements CommandExecutorI<ResultData<ApplyDetailCO>, QueryApplyDetailCmd> {

    @Resource
    ApplyMapper applyMapper;

    @Resource
    ApplyClientConverter converter;

    @Override
    public ResultData<ApplyDetailCO> execute(QueryApplyDetailCmd cmd) {
        ResultData<ApplyDetailCO> resultData = ResultData.success(null);
        check(cmd);
        ApplyDO detail = detail(cmd);
        ApplyDetailCO applyDetailCO = converter.dataToClient(detail);
        resultData.setData(applyDetailCO);
        return resultData;
    }

    protected boolean check(QueryApplyDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"审批id不能为空");
        return true;
    }

    public ApplyDO detail(QueryApplyDetailCmd cmd) {
        return applyMapper.selectById(cmd.getId());
    }

}
