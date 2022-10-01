package org.xujin.janus.domain.api.service;

import com.google.common.collect.Lists;
import org.xujin.janus.domain.api.constant.ApiOperateTypeEnum;
import org.xujin.janus.domain.api.converter.ApiConverter;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.collect.ChangeInfoRequest;
import org.xujin.janus.domain.change.collect.ChangeItem;
import org.xujin.janus.domain.change.collect.ChangeTypeEnum;
import org.xujin.janus.domain.change.submit.SubmitChangeRequest;
import org.xujin.janus.domain.user.service.ClusterService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/9 15:17
 **/
@Component
public class ApiChangeCollector {

    @Autowired
    private ApiService apiService;

    @Autowired
    private ApiConverter apiConverter;

    @Autowired
    private OutServiceConfigService outServiceConfigService;

    @Autowired
    private ClusterService clusterService;


    public ChangeInfo getChangeInfo(ChangeInfoRequest request) {
        ChangeInfo changeInfo = new ChangeInfo();
        List<ApiDraftE> draftEList = apiService.findDraftInEditing(request.getClusterCode());

        clusterService.fillClusterNameByCode(draftEList, ApiDraftE::getClusterCode, ApiDraftE::setClusterName);

        changeInfo.setChangeItems(assembleChangeItemList(draftEList));
        return changeInfo;
    }

    private ChangeItem convertToChangeItem(ApiDraftE draftE, List<OutServiceVO> outServices) {
        ChangeItem item = new ChangeItem();
        item.setResourceId(draftE.getId());
        item.setChangeType(convertToChangeType(draftE.getOperateType()));
        item.setNewData(apiConverter.convertDraftToVo(draftE, outServices));
        return item;
    }

    public ChangeInfo getChangeInfoForSubmit(SubmitChangeRequest request) {
        ChangeInfo changeInfo = new ChangeInfo();
        List<ApiDraftE> draftEList = apiService.findDraftByIdList(request.getResourceIdList());
        changeInfo.setChangeItems(assembleChangeItemList(draftEList));
        return changeInfo;
    }

    private List<ChangeItem> assembleChangeItemList(List<ApiDraftE> draftEList) {
        if (CollectionUtils.isEmpty(draftEList)) {
            return Lists.newArrayList();
        }
        List<Long> draftIdList = Lists.transform(draftEList, ApiDraftE::getId);
        Map<Long, List<OutServiceVO>> apiDraftIdToOutServices = outServiceConfigService.findByApiDraftIdList(draftIdList);
        return draftEList
                .stream()
                .filter(Objects::nonNull)
                .map(draftE -> convertToChangeItem(draftE, apiDraftIdToOutServices.get(draftE.getId())))
                .collect(Collectors.toList());
    }

    private ChangeTypeEnum convertToChangeType(ApiOperateTypeEnum operateType) {
        Validate.notNull(operateType, "operateType缺失");
        switch (operateType) {
            case NEW:
            case ONLINE:
                return ChangeTypeEnum.ADD;
            case UPDATE:
                return ChangeTypeEnum.UPDATE;
            case OFFLINE:
            case DELETE:
                return ChangeTypeEnum.DELETE;
            default:
                throw new IllegalStateException("operateType状态未知");
        }
    }

}
