package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.FilterRepository;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:17
 * @desc
 */
@Entity
@Data
public class FilterE {

    private FilterRepository repository;

    private BigInteger id;

    private String name;

    private Byte isGlobal;

    private Byte isShare;

    private String filterArgs;

    private Integer filterOrder;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Byte isDeleted;

    private String creator;

    private String modifier;

    private String clusterCode;

    private String classCode;

    private String description;
    private String status;

    private String filterCode;
    private String executePlace;

    @DomainAbility
    public void save() {
        repository.save(this);
    }

    @DomainAbility
    public void delete() {
        repository.delete(this);
    }
}
