package org.xujin.janus.domain.user.entity;

import com.google.common.collect.Lists;
import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.repository.PublishIpRepository;
import lombok.Data;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:59
 * @desc
 */
@Entity
@Data
public class PublishIpE {

    private PublishIpRepository publishIpRepository;

    @DomainAbility
    public void save() {
        publishIpRepository.save(this);
    }

    public boolean isWaitRelease() {
        return ReleaseStatusEnum.WAIT_RELEASE == status;
    }

    public void updateStatusToPulling() {
        updateStatus(ReleaseStatusEnum.PULLING);
    }

    public void updateStatusToReleasing() {
        updateStatus(ReleaseStatusEnum.RELEASING);
    }

    public void updateStatusToCompleted() {
        updateStatus(ReleaseStatusEnum.COMPLETED);
    }

    public void updateStatusToIgnored() {
        updateStatus(ReleaseStatusEnum.IGNORED);
    }

    public boolean canRelease() {
        return canReleaseStatus.contains(status);
    }

    public boolean isGrayPublishCompleted() {
        return ReleaseStatusEnum.COMPLETED == status;
    }

    public boolean isFullPublishCompleted() {
        return ReleaseStatusEnum.COMPLETED == status || ReleaseStatusEnum.IGNORED == status;
    }

    private boolean updateStatus(ReleaseStatusEnum status) {
        this.status = status;
        return publishIpRepository.updateStatus(id, status) > 0;
    }

    private static List<ReleaseStatusEnum> canReleaseStatus = Lists.newArrayList(
            ReleaseStatusEnum.WAIT_RELEASE, ReleaseStatusEnum.PULL_SUCCESS, ReleaseStatusEnum.RELEASE_FAIL
    );


    private Long id;
    private Long publishId;
    private String ip;
    private ReleaseTypeEnum type;
    private ReleaseStatusEnum status;
    private String creator;
    private String modifier;
    private List<String> ips;

}

