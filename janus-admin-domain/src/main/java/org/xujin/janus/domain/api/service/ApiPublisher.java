package org.xujin.janus.domain.api.service;

import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.entity.ApiReleaseE;
import org.xujin.janus.domain.api.factory.ApiFactory;
import org.xujin.janus.domain.change.release.EndReleaseChangesRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/20 16:45
 **/
@Slf4j
@Component
public class ApiPublisher {

    @Autowired
    private ApiFactory apiFactory;

    @Autowired
    private OutServiceConfigService outServiceConfigService;

    @Transactional(rollbackFor = Throwable.class)
    public void release(EndReleaseChangesRequest request) {
        List<ApiDraftE> draftEList = apiFactory.findApiDraftEListByApplyId(request.getApplyId());
        if (CollectionUtils.isEmpty(draftEList)) {
            return;
        }
        draftEList.forEach(draft -> releaseDraft(draft, request));
    }

    public void releaseDraft(ApiDraftE draftE, EndReleaseChangesRequest request) {
        try {
            doReleaseDraft(draftE, request);
        } catch (Exception ex) {
            String msg = "发布API到正式环境失败，ApplyId:" + request.getApplyId();
            log.error(msg, ex);
            throw new BusinessException("500", msg);
        }
    }

    public void doReleaseDraft(ApiDraftE draftE, EndReleaseChangesRequest request) {
        ApiReleaseE releaseE = createReleaseE(draftE, request.getCurrUser());

        boolean result = false;

        switch (draftE.getOperateType()) {
            case NEW:
                result = releaseForOnlineNew(draftE.getId(), releaseE);
                break;
            case UPDATE:
                result = releaseForUpdate(draftE.getId(), releaseE);
                break;
            case DELETE:
                result = releaseForDelete(releaseE);
                break;
            case ONLINE:
                result = releaseE.releaseForOnline();
                break;
            case OFFLINE:
                result = releaseE.releaseForOffline();
                break;
        }
        if (!result) {
            throw new BusinessException("500", "发布Api草稿失败, id: " + draftE.getId());
        }

        if (!draftE.updateToReleased()) {
            throw new BusinessException("500", "更新Api草稿状态失败，id: " + draftE.getId());
        }
    }

    private boolean releaseForOnlineNew(Long apiDraftId, ApiReleaseE releaseE) {
        if (!releaseE.releaseForOnlineNew()) {
            return false;
        }
        return outServiceConfigService.release(releaseE.getClusterCode(), apiDraftId, releaseE.getId());
    }

    private boolean releaseForUpdate(Long apiDraftId, ApiReleaseE releaseE) {
        if (!releaseE.releaseForUpdate()) {
            return false;
        }
        return outServiceConfigService.release(releaseE.getClusterCode(), apiDraftId, releaseE.getId());
    }

    private boolean releaseForDelete(ApiReleaseE releaseE) {
        if (!releaseE.releaseForDelete()) {
            return false;
        }
        outServiceConfigService.deleteConfigInRelease(releaseE.getId());
        return true;
    }

    private ApiReleaseE createReleaseE(ApiDraftE draftE, String currUser) {
        ApiReleaseE releaseE = apiFactory.createApiReleaseE(draftE);
        releaseE.setUpdateUser(currUser);
        if (draftE.isNewApi()) {
            releaseE.setCreateUser(releaseE.getUpdateUser());
        }
        return releaseE;
    }

}
