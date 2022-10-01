package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.ClusterUserRepository;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:22
 * @desc
 */
@Entity
@Data
public class ClusterUserE {

    private ClusterUserRepository clusterUserRepository;

    private BigInteger id;

    private String userName;

    private String clusterCode;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Byte isDeleted;


    private List<String> addList;

    public void save() {
        clusterUserRepository.save(this);
    }

    public void update() {
        clusterUserRepository.update(this);
    }

    public void delete() {
        clusterUserRepository.delete(this);
    }
}
