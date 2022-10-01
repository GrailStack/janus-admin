package org.xujin.janus.infrastructure.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.AlarmE;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglong.ren
 * @date 2020/4/15 10:16
 * @desc
 */
@Component
public class AlarmConverter implements ConverterI {

    public AlarmDO entityToData(AlarmE alarmE) {
        if (null == alarmE) {
            return null;
        }
        AlarmDO alarmDO = BeanMapper.map(alarmE, AlarmDO.class);
        return alarmDO;
    }


    public AlarmE entityToData(AlarmDO alarmDO) {
        if (null == alarmDO) {
            return null;
        }
        AlarmE alarmE = BeanMapper.map(alarmDO, AlarmE.class);
        return alarmE;
    }
}
