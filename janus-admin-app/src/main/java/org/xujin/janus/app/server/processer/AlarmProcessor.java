package org.xujin.janus.app.server.processer;

import org.xujin.halo.annotation.app.AppService;
import org.xujin.janus.client.cmo.AlarmCmd;
import org.xujin.janus.client.cmo.HeartBeatCmd;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.xujin.janus.damon.processer.ICmdMsgProcessor;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/6/12 10:30
 * @desc
 */
@AppService
public class AlarmProcessor  extends AbstractProcessor {

    @Autowired
    private AlarmMapper alarmMapper;

    @Override
    public JanusCmdMsg doExecute(JanusCmdMsg msg) {
        AlarmCmd alarmCmd = (AlarmCmd) msg.getPayload();
        AlarmDO alarmDO = new AlarmDO();
        alarmDO.setClusterCode(msg.getCluster());
        alarmDO.setInstanceHost(alarmCmd.getHost());
        alarmDO.setAlarmInfo(alarmCmd.getAlarmInfo());
        alarmDO.setStatus(new Byte("0"));
        addAlarm(alarmDO);
        return successResponse(msg,alarmCmd);
    }

    public void addAlarm(AlarmDO alarmDO) {
        alarmMapper.insert(alarmDO);
    }

}
