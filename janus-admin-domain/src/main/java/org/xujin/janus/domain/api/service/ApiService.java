package org.xujin.janus.domain.api.service;

import com.google.common.collect.Lists;
import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.repository.ApiDraftRepository;
import org.xujin.janus.domain.api.repository.ApiReleaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/5/29 15:28
 **/
@Slf4j
@DomainService
public class ApiService {

    @Autowired
    private ApiReleaseRepository releaseRepository;

    @Autowired
    private ApiDraftRepository draftRepository;

    public boolean isNameExistInCluster(String clusterCode, String name) {
        if (releaseRepository.containsNameInCluster(clusterCode, name)) {
            return true;
        }
        return draftRepository.containsNameInCluster(clusterCode, name);
    }

    public boolean isApiExistInCluster(String clusterCode, String path, String method) {
        if (releaseRepository.containsApiInCluster(clusterCode, path, method)) {
            return true;
        }
        return draftRepository.containsApiInCluster(clusterCode, path, method);
    }

    public List<ApiDraftE> findDraftInEditing(String clusterCode) {
        return draftRepository.findInEditing(clusterCode);
    }

    public int batchSubmitToAudit(List<Long> apiDraftIdList, Long applyId) {
        return draftRepository.batchSubmitToAudit(apiDraftIdList, applyId);
    }

    public int updateDraftStatusByApplyId(Long applyId, ApiDraftStatusEnum status) {
        return draftRepository.updateStatusByApplyId(applyId, status);
    }

    public List<ApiDraftE> findDraftByIdList(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        return draftRepository.findByIdList(idList);
    }

}
