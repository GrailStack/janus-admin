package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.InstanceRepository;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Data
public class InstanceE {

    private InstanceRepository instanceRepository;


    private BigInteger id;

    /**
     * ip:端口
     */
    private String host;

    /**
     * 集群编码
     */
    private String clusterCode;

    /**
     * 实例状态：0 在线 1 下线
     */
    private Byte status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    private Timestamp gmtModified;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    private Byte isDeleted;

    @DomainAbility
    public void save() {
        instanceRepository.save(this);
    }

}
