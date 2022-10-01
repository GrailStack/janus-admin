package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.InstanceDetailCmd;
import org.xujin.janus.app.command.co.InstanceDetailCO;
import org.xujin.janus.app.converter.InstanceClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.InstanceMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/15 11:10
 * @desc
 */
@Slf4j
@CmdHandler
public class InstanceDetailCmdExe implements CommandExecutorI<ResultData<InstanceDetailCO>, InstanceDetailCmd> {

    @Resource
    InstanceMapper instanceMapper;

    @Resource
    InstanceClientConverter converter;

    @Override
    public ResultData<InstanceDetailCO> execute(InstanceDetailCmd cmd) {
        ResultData<InstanceDetailCO> resultData = ResultData.success(null);
        check(cmd);
        InstanceDO instanceDO = detail(cmd);
        InstanceDetailCO instanceDetailCO = converter.dataToClient(instanceDO);
        resultData.setData(instanceDetailCO);
        return resultData;
    }

    protected boolean check(InstanceDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"告警id不能为空");
        return true;
    }

    public InstanceDO detail(InstanceDetailCmd cmd) {
        return instanceMapper.selectById(cmd.getId());
    }

}
