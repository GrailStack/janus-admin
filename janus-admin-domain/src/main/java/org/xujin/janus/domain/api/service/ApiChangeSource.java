package org.xujin.janus.domain.api.service;

import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.api.constant.ApiConstant;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.change.ChangeSource;
import org.xujin.janus.domain.change.audit.AuditChangesRequest;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.collect.ChangeInfoRequest;
import org.xujin.janus.domain.change.release.EndReleaseChangesRequest;
import org.xujin.janus.domain.change.release.StartReleaseChangesRequest;
import org.xujin.janus.domain.change.source.ChangeSourceInfo;
import org.xujin.janus.domain.change.submit.SubmitChangeRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 20:06
 **/
@Component
public class ApiChangeSource implements ChangeSource {

    @Autowired
    private ApiChangeCollector apiChangeCollector;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ApiPublisher apiPublisher;

    @Override
    public ChangeSourceInfo getChangeSourceInfo() {
        return new ChangeSourceInfo(ApiConstant.API_CHANGE_SOURCE_CODE, ApiConstant.API_CHANGE_SOURCE_NAME);
    }

    @Override
    public ChangeInfo getChangeInfo(ChangeInfoRequest request) {
        ChangeInfo changeInfo = apiChangeCollector.getChangeInfo(request);
        changeInfo.setChangeSourceInfo(getChangeSourceInfo());
        return changeInfo;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void submitChanges(SubmitChangeRequest request) throws BusinessException {
        if (CollectionUtils.isEmpty(request.getResourceIdList())) {
            return;
        }
        List<Long> apiDraftIdList = request.getResourceIdList();
        int updateCount = apiService.batchSubmitToAudit(apiDraftIdList, request.getApplyId());
        if (updateCount != apiDraftIdList.size()) {
            throw new BusinessException("500", getChangeSourceInfo().getSourceName() + "中部分变更提交审核失败，请刷新确认后重试");
        }
    }

    @Override
    public ChangeInfo getChangeInfoForSubmit(SubmitChangeRequest request) {
        ChangeInfo changeInfo = apiChangeCollector.getChangeInfoForSubmit(request);
        changeInfo.setChangeSourceInfo(getChangeSourceInfo());
        return changeInfo;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void auditChanges(AuditChangesRequest request) {
        ApiDraftStatusEnum status = ApiDraftStatusEnum.valueOf(request.getAction().getCode());
        apiService.updateDraftStatusByApplyId(request.getApplyId(), status);
    }

    @Override
    public void startReleaseChanges(StartReleaseChangesRequest request) throws BusinessException {
        apiService.updateDraftStatusByApplyId(request.getApplyId(), ApiDraftStatusEnum.RELEASING);
    }

    @Override
    public void endReleaseChanges(EndReleaseChangesRequest request) {
        apiPublisher.release(request);
    }

}
