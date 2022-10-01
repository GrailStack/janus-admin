package org.xujin.janus.domain.user.ports;

import org.xujin.janus.domain.user.entity.AlarmE;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:48
 * @desc
 */
public interface AlarmPort {

    /**
     * 更新告警
     * @param alarmE
     */
    void update(AlarmE alarmE);
}
