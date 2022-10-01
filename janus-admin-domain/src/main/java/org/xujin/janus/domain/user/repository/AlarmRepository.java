package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.entity.AlarmE;
import org.xujin.janus.domain.user.ports.AlarmPort;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:49
 * @desc
 */
@DomainRepository
public class AlarmRepository {

    @Resource
    private AlarmPort  alarmPort;

    public void update(AlarmE alarmE) {
        alarmPort.update(alarmE);
    }
}
