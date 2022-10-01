package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.ClusterFilterRepository;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:48
 * @desc
 */
@Entity
@Data
public class ClusterFilterE {
    private ClusterFilterRepository clusterFilterRepository;
    private BigInteger id;
    private String clusterCode;
    private BigInteger filterId;
    private Byte isDelete;

    @DomainAbility
    public void save() {
        clusterFilterRepository.save(this);
    }
}
