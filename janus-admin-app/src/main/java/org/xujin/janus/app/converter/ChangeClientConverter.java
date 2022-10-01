package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.janus.app.command.co.ChangeInfoCO;
import org.xujin.janus.app.command.co.ChangeItemCO;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.collect.ChangeItem;
import org.xujin.janus.domain.change.collect.ChangeTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 19:55
 **/
@Component
public class ChangeClientConverter {

    public List<ChangeInfoCO> convertChangeInfoList(List<ChangeInfo> changeInfoList) {
        if (CollectionUtils.isEmpty(changeInfoList)) {
            return Lists.newArrayList();
        }
        return changeInfoList
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertChangeInfo)
                .collect(Collectors.toList());
    }

    public ChangeInfoCO convertChangeInfo(ChangeInfo changeInfo) {
        ChangeInfoCO co = new ChangeInfoCO();
        co.setSourceCode(changeInfo.getChangeSourceInfo().getSourceCode());
        co.setSourceName(changeInfo.getChangeSourceInfo().getSourceName());
        Map<ChangeTypeEnum, List<ChangeItem>> type2items = changeInfo.groupByChangeType();

        List<ChangeItemCO> items = Lists.newArrayList();
        co.setChangeItems(items);

        List<ChangeItemCO> deleteItems = convertItemList(type2items.get(ChangeTypeEnum.DELETE));
        items.addAll(deleteItems);
        co.setDeleteCount(deleteItems.size());

        List<ChangeItemCO> updateItems = convertItemList(type2items.get(ChangeTypeEnum.UPDATE));
        items.addAll(updateItems);
        co.setUpdateCount(updateItems.size());

        List<ChangeItemCO> addItems = convertItemList(type2items.get(ChangeTypeEnum.ADD));
        items.addAll(addItems);
        co.setAddCount(addItems.size());

        co.setTotalCount(co.getDeleteCount() + co.getUpdateCount() + co.getAddCount());

        return co;
    }

    private List<ChangeItemCO> convertItemList(List<ChangeItem> items) {
        return Optional
                .ofNullable(items)
                .orElse(Lists.newArrayList())
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertItem)
                .collect(Collectors.toList());
    }

    private ChangeItemCO convertItem(ChangeItem item) {
        ChangeItemCO co = new ChangeItemCO();
        co.setChangeType(item.getChangeType().getCode());
        co.setChangeTypeDesc(item.getChangeType().getDesc());
        co.setResourceId(item.getResourceId());
        co.setNewData(item.getNewData());
        co.setOldData(item.getOldData());
        return co;
    }

}
