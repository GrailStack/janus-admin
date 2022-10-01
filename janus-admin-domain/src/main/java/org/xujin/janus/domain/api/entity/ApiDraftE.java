package org.xujin.janus.domain.api.entity;

import com.google.common.collect.Lists;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.api.constant.*;
import org.xujin.janus.domain.api.constant.*;
import org.xujin.janus.domain.api.repository.ApiDraftRepository;
import org.xujin.janus.domain.api.valueobject.ApiParamTransConfig;
import lombok.Data;
import org.apache.commons.lang3.Validate;

import java.util.Date;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/5/29 15:27
 **/
@Entity
@Data
public class ApiDraftE {

    private ApiDraftRepository draftRepository;

    public void save() {
        if (id == null) {
            saveForNewDraft();
        } else {
            saveForUpdateDraft();
        }
    }

    private void saveForNewDraft() {
        Long addResult = draftRepository.add(this);
        if (addResult < 1) {
            throw new BusinessException("500", "创建ApiInDraft失败");
        }
        this.id = addResult;
        if (this.apiId == null && !draftRepository.updateApiIdToId(id)) {
            throw new BusinessException("500", "创建ApiInDraft失败");
        }
    }

    private void saveForUpdateDraft() {
        int updateResult = draftRepository.update(this);
        if (updateResult < 1) {
            throw new BusinessException("500", "更新ApiInDraft失败");
        }
    }

    public boolean updateToAuditing() {
        Validate.notNull(id, "id缺失");
        status = ApiDraftStatusEnum.WAIT_AUDIT;
        return draftRepository.updateStatusById(id, ApiDraftStatusEnum.WAIT_AUDIT);
    }

    public boolean updateToReleased() {
        Validate.notNull(id, "id缺失");
        status = ApiDraftStatusEnum.RELEASED;
        return draftRepository.updateStatusById(id, ApiDraftStatusEnum.RELEASED);
    }

    public boolean delete() throws BusinessException {
        return draftRepository.logicalDeleteWithCheck(id, deletableStatus);
    }

    public boolean canDelete() {
        Validate.notNull(status, "status缺失");
        return deletableStatus.contains(status);
    }

    public boolean isNewApi() {
        Validate.notNull(operateType, "operateType缺失");
        return ApiOperateTypeEnum.NEW == operateType;
    }

    public boolean hasReleased() {
        Validate.notNull(status, "status缺失");
        return ApiDraftStatusEnum.RELEASED == status;
    }

    public boolean canEdit() {
        Validate.notNull(operateType, "operateType缺失");
        Validate.notNull(status, "status缺失");
        return editableOptTypes.contains(operateType) && editableStatus.contains(status);
    }

    public boolean canCommitToAudit() {
        Validate.notNull(status, "status缺失");
        return canAuditStatus.contains(status);
    }

    private static List<ApiOperateTypeEnum> editableOptTypes = Lists.newArrayList(
            ApiOperateTypeEnum.NEW, ApiOperateTypeEnum.UPDATE
    );

    private static List<ApiDraftStatusEnum> editableStatus = Lists.newArrayList(
            ApiDraftStatusEnum.EDITING, ApiDraftStatusEnum.WAIT_AUDIT,
            ApiDraftStatusEnum.AUDITED, ApiDraftStatusEnum.REJECTED
    );

    private static List<ApiDraftStatusEnum> canAuditStatus = Lists.newArrayList(
            ApiDraftStatusEnum.EDITING
    );

    private static List<ApiDraftStatusEnum> deletableStatus = Lists.newArrayList(
            ApiDraftStatusEnum.EDITING, ApiDraftStatusEnum.REJECTED, ApiDraftStatusEnum.CANCELED,
            ApiDraftStatusEnum.AUDITED
    );

    private Long id;
    private Long apiId;
    private String name;
    private String description;
    private String clusterCode;
    private String clusterName;
    private ApiProtocolEnum protocol;
    private String path;
    private HttpMethodEnum method;
    private ApiParamTransTypeEnum paramTransType;
    private ApiParamTransConfig paramTransConfig;
    private String createUser;
    private String updateUser;
    private Date createTime;
    private Date updateTime;
    private Integer version;
    private Boolean isDeleted;
    private ApiOperateTypeEnum operateType;
    private ApiDraftStatusEnum status;
    private Long applyId;

}
