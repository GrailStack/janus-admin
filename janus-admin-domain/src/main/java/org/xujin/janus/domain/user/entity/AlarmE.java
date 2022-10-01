package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.AlarmRepository;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author chenglong.ren
 * @date 2020/4/14 18:41
 * @desc
 */
@Entity
@Data
public class AlarmE {
    private AlarmRepository alarmRepository;

    private Long id;

    private String clusterCode;

    private String instanceHost;

    private String alarmInfo;

    private Byte status;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Byte isDeleted;

    @DomainAbility
    public void update() {
        alarmRepository.update(this);
    }
}
