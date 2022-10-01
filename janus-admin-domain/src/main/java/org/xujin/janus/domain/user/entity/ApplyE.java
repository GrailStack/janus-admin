package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.change.constant.AuditActionEnum;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.repository.ApplyRepository;
import lombok.Data;

import java.util.Date;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:59
 * @desc
 */
@Entity
@Data
public class ApplyE {

    private ApplyRepository applyRepository;

    public boolean saveChanges(String changes) {
        this.changes = changes;
        return applyRepository.updateChanges(id, changes);
    }

    public boolean audit(AuditActionEnum action, String approver) throws BusinessException {
        ApplyStatusEnum status = ApplyStatusEnum.valueOf(action.getCode());
        return applyRepository.updateStatus(id, status, approver);
    }

    public boolean canAudit() {
        return status == ApplyStatusEnum.WAIT_AUDIT;
    }

    public boolean updateStatusToReleasing() {
        status = ApplyStatusEnum.RELEASING;
        return applyRepository.updateStatus(id, status);
    }

    public boolean canRelease() {
        return status == ApplyStatusEnum.AUDITED;
    }

    @DomainAbility
    public void save() {
        applyRepository.save(this);
    }

    @DomainAbility
    public void delete() {
        applyRepository.delete(this);
    }

    @DomainAbility
    public void update() {
        applyRepository.update(this);
    }

    private boolean updateStatus(ApplyStatusEnum status) {
        return applyRepository.updateStatus(id, status);
    }

    private Long id;
    private String clusterCode;
    private String description;
    private String request;
    private String changes;
    private String creator;
    private String approver;
    private String publisher;
    private ApplyStatusEnum status;
    private Date gmtCreate;
    private Date gmtModified;
    private Byte isDeleted;

}
