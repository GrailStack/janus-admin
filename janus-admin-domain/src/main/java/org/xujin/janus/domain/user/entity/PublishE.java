package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.repository.PublishRepository;
import lombok.Data;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:59
 * @desc
 */
@Entity
@Data
public class PublishE {

    private PublishRepository publishRepository;

    @DomainAbility
    public void save() {
        publishRepository.save(this);
    }

    public boolean isPublishCompleted() {
        return ReleaseStatusEnum.COMPLETED == status;
    }

    public boolean isGrayPublish() {
        return ReleaseTypeEnum.GRAY == type;
    }

    public boolean updateStatusToCompleted() {
        return updateStatus(ReleaseStatusEnum.COMPLETED);
    }

    private boolean updateStatus(ReleaseStatusEnum status) {
        this.status = status;
        return publishRepository.updateStatus(id, status);
    }

    private Long id;
    private Long applyId;
    private ReleaseTypeEnum type;
    private String publisher;
    /**
     * RELEASING, COMPLETED
     */
    private ReleaseStatusEnum status;
    private String clusterCode;
    private String creator;
    private String modifier;

}

