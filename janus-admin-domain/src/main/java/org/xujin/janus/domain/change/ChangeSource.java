package org.xujin.janus.domain.change;

import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.change.audit.AuditChangesRequest;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.collect.ChangeInfoRequest;
import org.xujin.janus.domain.change.release.EndReleaseChangesRequest;
import org.xujin.janus.domain.change.release.StartReleaseChangesRequest;
import org.xujin.janus.domain.change.source.ChangeSourceInfo;
import org.xujin.janus.domain.change.submit.SubmitChangeRequest;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 16:08
 **/
public interface ChangeSource {

    ChangeSourceInfo getChangeSourceInfo();

    ChangeInfo getChangeInfo(ChangeInfoRequest request);

    void submitChanges(SubmitChangeRequest request) throws BusinessException;

    ChangeInfo getChangeInfoForSubmit(SubmitChangeRequest request);

    void auditChanges(AuditChangesRequest request);

    void startReleaseChanges(StartReleaseChangesRequest request) throws BusinessException;

    void endReleaseChanges(EndReleaseChangesRequest request);

}
