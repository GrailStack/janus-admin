package org.xujin.janus.domain.api.entity;

import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.api.constant.*;
import org.xujin.janus.domain.api.constant.*;
import org.xujin.janus.domain.api.repository.ApiReleaseRepository;
import org.xujin.janus.domain.api.valueobject.ApiParamTransConfig;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Date;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/5/29 14:23
 **/
@Entity
@Data
public class ApiReleaseE {

    private ApiReleaseRepository releaseRepository;

    public boolean releaseForOnlineNew() {
        Validate.notNull(id, "id缺失");
        status = ApiStatusEnum.ONLINE;
        version = 1;
        lockType = "";
        return releaseRepository.add(this);
    }

    public boolean releaseForUpdate() {
        Validate.notNull(id, "id缺失");
        status = null;
        version++;
        lockType = "";
        return releaseRepository.updateWithReleaseLock(this, ApiOperateTypeEnum.UPDATE.getCode());
    }

    public boolean releaseForDelete() {
        Validate.notNull(id, "id缺失");
        lockType = "";
        return releaseRepository.deleteWithReleaseLockCheck(id, version, ApiOperateTypeEnum.DELETE.getCode());
    }

    public boolean releaseForOnline() {
        Validate.notNull(id, "id缺失");
        lockType = "";
        return releaseRepository.updateStatusWithReleaseLock(id, version, ApiStatusEnum.ONLINE, ApiOperateTypeEnum.ONLINE.getCode());
    }

    public boolean releaseForOffline() {
        Validate.notNull(id, "id缺失");
        lockType = "";
        return releaseRepository.updateStatusWithReleaseLock(id, version, ApiStatusEnum.OFFLINE, ApiOperateTypeEnum.OFFLINE.getCode());
    }

    public boolean releaseLock(String currLockType) {
        Validate.notNull(id, "id缺失");
        return releaseRepository.releaseLock(id, version, currLockType);
    }

    public boolean isOnline() {
        return this.status == ApiStatusEnum.ONLINE;
    }

    public boolean isOffline() {
        return this.status == ApiStatusEnum.OFFLINE;
    }

    public boolean lock(String lockType) {
        Validate.notEmpty(lockType, "lockType缺失");
        Validate.notNull(id, "id缺失");
        boolean locked = releaseRepository.lock(id, version, lockType);
        if (locked) {
            this.lockType = lockType;
        }
        return locked;
    }

    public boolean isLocked() {
        return StringUtils.isNotBlank(lockType);
    }

    private Long id;
    private String name;
    private String description;
    private String clusterCode;
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
    private ApiStatusEnum status;
    private String lockType;

}
