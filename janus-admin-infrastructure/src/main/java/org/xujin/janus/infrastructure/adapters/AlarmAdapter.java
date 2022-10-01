package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.AlarmE;
import org.xujin.janus.domain.user.ports.AlarmPort;
import org.xujin.janus.infrastructure.converter.AlarmConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/6/8 14:10
 * @desc
 */
@Adapter
public class AlarmAdapter implements AlarmPort {

    @Resource
    private AlarmMapper alarmMapper;

    @Resource
    private AlarmConverter alarmConverter;

    @Override
    public void update(AlarmE alarmE) {
        AlarmDO alarmDO = alarmConverter.entityToData(alarmE);
        alarmMapper.updateById(alarmDO);
    }
}
