package org.xujin.janus.domain.change.impl;

import com.google.common.collect.Lists;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.change.ChangeManager;
import org.xujin.janus.domain.change.ChangeSource;
import org.xujin.janus.domain.change.audit.AuditChangesRequest;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.collect.ChangeInfoRequest;
import org.xujin.janus.domain.change.release.EndReleaseChangesRequest;
import org.xujin.janus.domain.change.release.StartReleaseChangesRequest;
import org.xujin.janus.domain.change.source.ChangeSourceInfo;
import org.xujin.janus.domain.change.submit.SubmitChangeRequest;
import org.xujin.janus.domain.change.submit.SubmitMultiChangeRequest;
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
 * @date 2020/6/11 16:04
 **/
@Slf4j
@Component
public class ChangeManagerImpl implements ChangeManager {

    @Autowired(required = false)
    private List<ChangeSource> changeSources;

    @Override
    public List<ChangeInfo> collectChanges(ChangeInfoRequest request) {
        List<ChangeInfo> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(changeSources)) {
            return list;
        }
        for (ChangeSource changeSource : changeSources) {
            ChangeInfo changeInfo = changeSource.getChangeInfo(request);
            if (changeInfo.hasChangeItems()) {
                list.add(changeInfo);
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void submitChanges(SubmitMultiChangeRequest multiChangesRequest) throws BusinessException {
        for (SubmitChangeRequest request : multiChangesRequest.getSubmitChanges()) {
            ChangeSource changeSource = findChangeSource(request.getSourceCode());
            if (changeSource == null) {
                throw new BusinessException("400", "sourceCode无效");
            }
            request.setApplyId(multiChangesRequest.getApplyId());
            request.setClusterCode(multiChangesRequest.getClusterCode());
            request.setDescription(multiChangesRequest.getDescription());

            changeSource.submitChanges(request);
        }
    }

    @Override
    public List<ChangeInfo> collectSubmitChanges(SubmitMultiChangeRequest multiChangesRequest) {
        List<ChangeInfo> list = Lists.newArrayList();
        for (SubmitChangeRequest request : multiChangesRequest.getSubmitChanges()) {
            ChangeSource changeSource = findChangeSource(request.getSourceCode());
            if (changeSource == null) {
                throw new BusinessException("400", "sourceCode无效");
            }
            ChangeInfo changeInfo = changeSource.getChangeInfoForSubmit(request);
            if (changeInfo.hasChangeItems()) {
                list.add(changeInfo);
            }
        }
        return list;
    }

    @Override
    public void auditChanges(AuditChangesRequest request) {
        if (CollectionUtils.isEmpty(changeSources)) {
            return;
        }
        for (ChangeSource changeSource : changeSources) {
            changeSource.auditChanges(request);
        }
    }

    @Override
    public void startReleaseChanges(StartReleaseChangesRequest request) throws BusinessException {
        if (CollectionUtils.isEmpty(changeSources)) {
            return;
        }
        for (ChangeSource changeSource : changeSources) {
            changeSource.startReleaseChanges(request);
        }
    }

    @Override
    public void endReleaseChanges(EndReleaseChangesRequest request) throws BusinessException {
        if (CollectionUtils.isEmpty(changeSources)) {
            return;
        }
        for (ChangeSource changeSource : changeSources) {
            changeSource.endReleaseChanges(request);
        }
    }

    private ChangeSource findChangeSource(String sourceCode) {
        if (CollectionUtils.isEmpty(changeSources)) {
            return null;
        }
        for (ChangeSource changeSource : changeSources) {
            ChangeSourceInfo changeSourceInfo = changeSource.getChangeSourceInfo();
            if (changeSourceInfo.getSourceCode().equals(sourceCode)) {
                return changeSource;
            }
        }
        return null;
    }

}
