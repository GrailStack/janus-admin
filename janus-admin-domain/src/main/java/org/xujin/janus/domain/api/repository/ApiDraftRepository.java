package org.xujin.janus.domain.api.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.port.ApiDraftPort;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 20:42
 **/
@DomainRepository
public class ApiDraftRepository {

    @Autowired
    private ApiDraftPort draftPort;

    public Long add(ApiDraftE draftE) {
        return draftPort.add(draftE);
    }

    public boolean updateApiIdToId(Long id) {
        return draftPort.updateApiIdToId(id);
    }

    public ApiDraftE findApiDraftE(Long id) {
        ApiDraftE draftE = draftPort.findUnDeletedById(id);
        if (draftE == null) {
            return null;
        }
        draftE.setDraftRepository(this);
        return draftE;
    }

    public boolean updateStatusById(Long id, ApiDraftStatusEnum status) {
        return draftPort.updateStatusById(id, status);
    }

    public boolean logicalDeleteWithCheck(Long id, List<ApiDraftStatusEnum> deletableStatus) {
        return draftPort.logicalDeleteWithCheck(id, deletableStatus);
    }

    public boolean containsNameInCluster(String clusterCode, String name) {
        return draftPort.containsNameInCluster(clusterCode, name);
    }

    public boolean containsApiInCluster(String clusterCode, String path, String method) {
        return draftPort.containsApiInCluster(clusterCode, path, method);
    }

    public int update(ApiDraftE draftE) {
        return draftPort.update(draftE);
    }

    public List<ApiDraftE> findInEditing(String clusterCode) {
        return draftPort.findInEditing(clusterCode);
    }

    public int batchSubmitToAudit(List<Long> apiDraftIdList, Long applyId) {
        return draftPort.batchSubmitToAudit(apiDraftIdList, applyId);
    }

    public int updateStatusByApplyId(Long applyId, ApiDraftStatusEnum status) {
        return draftPort.updateStatusByApplyId(applyId, status);
    }

    public List<ApiDraftE> findByIdList(List<Long> idList) {
        List<ApiDraftE> draftList = draftPort.findByIdList(idList);
        setRepositoryForDraft(draftList);
        return draftList;
    }

    public List<ApiDraftE> findByApplyId(Long applyId) {
        List<ApiDraftE> draftList = draftPort.findByApplyId(applyId);
        setRepositoryForDraft(draftList);
        return draftList;
    }

    private void setRepositoryForDraft(List<ApiDraftE> draftList) {
        if (CollectionUtils.isEmpty(draftList)) {
            return;
        }
        draftList.forEach(draft -> draft.setDraftRepository(this));
    }

}
