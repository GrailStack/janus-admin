package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.AlarmE;
import org.xujin.janus.domain.user.repository.AlarmRepository;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/15 9:55
 * @desc
 */
@Factory
public class AlarmFactory {
    @Resource
    AlarmRepository alarmRepository;

    public AlarmE createAlarmEntity() {
        AlarmE alarmE = new AlarmE();
        alarmE.setAlarmRepository(alarmRepository);
        return alarmE;
    }
}
