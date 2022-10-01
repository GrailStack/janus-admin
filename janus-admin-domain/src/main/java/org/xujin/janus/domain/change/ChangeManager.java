package org.xujin.janus.domain.change;

import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.change.audit.AuditChangesRequest;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.collect.ChangeInfoRequest;
import org.xujin.janus.domain.change.release.EndReleaseChangesRequest;
import org.xujin.janus.domain.change.release.StartReleaseChangesRequest;
import org.xujin.janus.domain.change.submit.SubmitMultiChangeRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 16:05
 **/
@Component
public interface ChangeManager {

    List<ChangeInfo> collectChanges(ChangeInfoRequest request);

    void submitChanges(SubmitMultiChangeRequest multiChangesRequest) throws BusinessException;

    List<ChangeInfo> collectSubmitChanges(SubmitMultiChangeRequest multiChangesRequest);

    void auditChanges(AuditChangesRequest request) throws BusinessException;

    void startReleaseChanges(StartReleaseChangesRequest request) throws BusinessException;

    void endReleaseChanges(EndReleaseChangesRequest request) throws BusinessException;

}
