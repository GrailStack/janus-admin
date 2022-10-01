package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.AlarmDetailCmd;
import org.xujin.janus.app.command.co.AlarmDetailCO;
import org.xujin.janus.app.converter.AlarmClientConverter;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
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
public class AlarmDetailCmdExe implements CommandExecutorI<ResultData<AlarmDetailCO>, AlarmDetailCmd> {

    @Resource
    AlarmMapper alarmMapper;

    @Resource
    AlarmClientConverter converter;

    @Override
    public ResultData<AlarmDetailCO> execute(AlarmDetailCmd cmd) {
        ResultData<AlarmDetailCO> resultData = ResultData.success(null);
        check(cmd);
        AlarmDO detail = detail(cmd);
        AlarmDetailCO alarmDetailCO = converter.dataToClient(detail);
        resultData.setData(alarmDetailCO);
        return resultData;
    }

    protected boolean check(AlarmDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"告警id不能为空");
        return true;
    }

    public AlarmDO detail(AlarmDetailCmd cmd) {
        return alarmMapper.selectById(cmd.getId());
    }

}
