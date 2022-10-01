package org.xujin.janus.domain.api.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.api.constant.ApiOperateTypeEnum;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.entity.ApiReleaseE;
import org.xujin.janus.domain.api.repository.ApiDraftRepository;
import org.xujin.janus.domain.api.repository.ApiReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/5/29 15:38
 **/
@Factory
public class ApiFactory {

    @Autowired
    private ApiDraftRepository draftRepository;

    @Autowired
    private ApiReleaseRepository releaseRepository;

    public ApiDraftE createApiInDraftEForNewApi() {
        ApiDraftE draftE = new ApiDraftE();
        draftE.setDraftRepository(draftRepository);
        draftE.setOperateType(ApiOperateTypeEnum.NEW);
        draftE.setStatus(ApiDraftStatusEnum.EDITING);
        draftE.setVersion(1);
        draftE.setId(null);
        draftE.setIsDeleted(false);
        return draftE;
    }

    public ApiDraftE createApiDraftE(Long apiDraftId) {
        return draftRepository.findApiDraftE(apiDraftId);
    }

    public ApiDraftE createApiDraftE() {
        ApiDraftE draftE = new ApiDraftE();
        draftE.setDraftRepository(draftRepository);
        return draftE;
    }

    public ApiReleaseE createApiReleaseE(Long apiInReleaseId) {
        return releaseRepository.finApiInReleaseE(apiInReleaseId);
    }

    public ApiReleaseE createApiReleaseE(ApiDraftE draftE) {
        ApiReleaseE releaseE = new ApiReleaseE();
        releaseE.setReleaseRepository(releaseRepository);
        releaseE.setId(draftE.getApiId());
        releaseE.setName(draftE.getName());
        releaseE.setDescription(draftE.getDescription());
        releaseE.setClusterCode(draftE.getClusterCode());
        releaseE.setProtocol(draftE.getProtocol());
        releaseE.setPath(draftE.getPath());
        releaseE.setMethod(draftE.getMethod());
        releaseE.setParamTransType(draftE.getParamTransType());
        releaseE.setParamTransConfig(draftE.getParamTransConfig());
        releaseE.setVersion(draftE.getVersion());
        return releaseE;
    }

    public ApiDraftE createApiDraftE(ApiReleaseE releaseE) {
        ApiDraftE draftE = createApiDraftE();
        draftE.setApiId(releaseE.getId());
        draftE.setName(releaseE.getName());
        draftE.setDescription(releaseE.getDescription());
        draftE.setClusterCode(releaseE.getClusterCode());
        draftE.setProtocol(releaseE.getProtocol());
        draftE.setPath(releaseE.getPath());
        draftE.setMethod(releaseE.getMethod());
        draftE.setParamTransType(releaseE.getParamTransType());
        draftE.setParamTransConfig(releaseE.getParamTransConfig());
        draftE.setVersion(releaseE.getVersion());
        draftE.setStatus(ApiDraftStatusEnum.EDITING);
        draftE.setIsDeleted(false);
        return draftE;
    }

    public List<ApiDraftE> findApiDraftEListByApplyId(Long applyId) {
        return draftRepository.findByApplyId(applyId);
    }

}
