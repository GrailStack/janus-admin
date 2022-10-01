package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.ClusterRepository;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author chenglong.ren
 * @date 2020/4/14 18:41
 * @desc
 */
@Entity
@Data
public class ClusterEntity {
    private ClusterRepository clusterRepository;

    private BigInteger id;

    private String code;

    private String name;

    private String ownerName;

    private String bizName;

    private String ownerId;

    private Byte status;

    private String domainName;

    private String description;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Byte isShare;

    private Byte isDeleted;

    private String created;

    private String updated;

    @DomainAbility
    public void save() {
        clusterRepository.save(this);
    }

    @DomainAbility
    public void delete() {
        clusterRepository.delete(this);
    }

    @DomainAbility
    public void update() {
        clusterRepository.updateCluster(this);
    }
}
