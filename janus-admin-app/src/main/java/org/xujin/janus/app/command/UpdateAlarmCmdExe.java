package org.xujin.janus.app.command;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.UpdateAlarmCmd;
import org.xujin.janus.app.converter.AlarmClientConverter;
import org.xujin.janus.domain.user.entity.AlarmE;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:48
 * @desc
 */
@Slf4j
@CmdHandler
public class UpdateAlarmCmdExe implements CommandExecutorI<ResultData, UpdateAlarmCmd> {

    @Resource
    AlarmClientConverter converter;
    @Resource
    private AlarmMapper alarmMapper;

    @Override
    public ResultData execute(UpdateAlarmCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        AlarmDO alarmDO = alarmMapper.selectById(cmd.getId());
        if (null == alarmDO) {
            throw new BusinessException("400", "告警信息不存在");
        }

        AlarmE alarmE = converter.updateCmdToEntity(cmd);
        alarmE.update();
        return resultData;
    }

    protected boolean check(UpdateAlarmCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getStatus(), "状态不能为null");
        return true;
    }
}
