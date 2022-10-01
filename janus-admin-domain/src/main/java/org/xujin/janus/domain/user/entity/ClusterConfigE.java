package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.ClusterConfigRepository;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/5/27 9:54
 * @desc
 */
@Entity
@Data
public class ClusterConfigE {
    private ClusterConfigRepository clusterConfigRepository;

    /**
     * id
     */
    private BigInteger id;

    /**
     * 集群编码
     */
    private String clusterCode;

    /**
     * 配置key
     */
    private String configKey;

    /**
     * 配置Value
     */
    private String configValue;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改者
     */
    private String modifier;

    private String name;
    private String type;
    private Byte status;

    @DomainAbility
    public void save() {
        clusterConfigRepository.save(this);
    }

    @DomainAbility
    public void update() {
        clusterConfigRepository.update(this);
    }

    public void delete() {
        clusterConfigRepository.delete(this);
    }
}
