package org.xujin.janus.domain.api.service;

import com.google.common.collect.Maps;
import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.api.repository.OutServiceDraftRepository;
import org.xujin.janus.domain.api.repository.OutServiceReleaseRepository;
import org.xujin.janus.domain.api.valueobject.OutServiceConfig;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:16
 **/
@Slf4j
@DomainService
public class OutServiceConfigService {

    @Autowired
    private OutServiceDraftRepository draftRepository;

    @Autowired
    private OutServiceReleaseRepository releaseRepository;

    @Transactional
    public void addConfigInDraft(String clusterCode, Long apiDraftId, OutServiceConfig outServiceConfig) {
        List<OutServiceVO> voList = outServiceConfig.getOutServices();
        if (CollectionUtils.isEmpty(voList)) {
            throw new BusinessException("400", "缺少后端服务配置信息");
        }
        for (OutServiceVO vo : voList) {
            check(vo);
        }
        for (OutServiceVO vo : voList) {
            draftRepository.add(clusterCode, apiDraftId, vo);
        }
    }

    private void check(OutServiceVO vo) {
        if (vo == null) {
            throw new BusinessException("400", "缺少后端服务配置信息");
        }
    }

    public int deleteConfigInDraft(Long apiDraftId) {
        return draftRepository.deleteByApiDraftId(apiDraftId);
    }

    public boolean release(String clusterCode, Long apiDraftId, Long apiReleaseId) {
        List<OutServiceVO> voList = draftRepository.findByApiDraftId(apiDraftId);
        if (CollectionUtils.isEmpty(voList)) {
            throw new BusinessException("400", "后端服务配置在草稿环境中不存在，apiDraftId:" + apiDraftId);
        }

        releaseRepository.deleteByApiReleaseId(apiReleaseId);

        for (OutServiceVO vo : voList) {
            if (!releaseRepository.add(clusterCode, apiDraftId, vo)) {
                return false;
            }
        }
        return true;
    }

    public int deleteConfigInRelease(Long apiReleaseId) {
        return releaseRepository.deleteByApiReleaseId(apiReleaseId);
    }

    public boolean copyFromReleaseToDraft(String clusterCode, Long apiReleaseId, Long apiDraftId) {
        List<OutServiceVO> voList = releaseRepository.findByApiReleaseId(apiReleaseId);
        if (CollectionUtils.isEmpty(voList)) {
            return false;
        }
        for (OutServiceVO vo : voList) {
            if (!draftRepository.add(clusterCode, apiDraftId, vo)) {
                return false;
            }
        }
        return true;
    }

    public Map<Long, List<OutServiceVO>> findByApiDraftIdList(List<Long> draftIdList) {
        if (CollectionUtils.isEmpty(draftIdList)) {
            return Maps.newHashMap();
        }
        return draftRepository.findByApiDraftIdList(draftIdList);
    }

}
