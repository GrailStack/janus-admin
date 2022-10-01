package org.xujin.janus.domain.change.collect;

import org.xujin.janus.domain.change.source.ChangeSourceInfo;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 16:17
 **/
@Data
public class ChangeInfo implements Serializable {

    private ChangeSourceInfo changeSourceInfo;

    private List<ChangeItem> changeItems;

    public boolean hasChangeItems() {
        return CollectionUtils.isNotEmpty(changeItems);
    }

    public void addChangeItem(ChangeItem item) {
        if (changeItems == null) {
            changeItems = new ArrayList<>();
        }
        changeItems.add(item);
    }

    public Map<ChangeTypeEnum, List<ChangeItem>> groupByChangeType() {
        return Optional
                .ofNullable(changeItems)
                .orElse(new ArrayList<>())
                .stream()
                .collect(Collectors.groupingBy(ChangeItem::getChangeType));
    }

}
