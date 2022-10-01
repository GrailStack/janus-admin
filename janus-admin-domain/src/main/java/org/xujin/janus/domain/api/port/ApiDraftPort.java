package org.xujin.janus.domain.api.port;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.api.entity.ApiDraftE;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 17:15
 **/
@Port
public interface ApiDraftPort {

    long add(ApiDraftE draftE);

    boolean updateApiIdToId(Long id);

    ApiDraftE findUnDeletedById(Long id);

    boolean updateStatusById(Long id, ApiDraftStatusEnum status);

    boolean logicalDeleteWithCheck(Long id, List<ApiDraftStatusEnum> noDeletableAuditStatus);

    boolean containsNameInCluster(String clusterCode, String name);

    boolean containsApiInCluster(String clusterCode, String path, String method);

    int update(ApiDraftE draftE);

    List<ApiDraftE> findInEditing(String clusterCode);

    int batchSubmitToAudit(List<Long> apiDraftIdList, Long applyId);

    int updateStatusByApplyId(Long applyId, ApiDraftStatusEnum status);

    List<ApiDraftE> findByIdList(List<Long> idList);

    List<ApiDraftE> findByApplyId(Long applyId);

}
