package org.xujin.janus.domain.change.collect;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 16:40
 **/
@Data
public class ChangeItem implements Serializable {

    private ChangeTypeEnum changeType;

    private Long resourceId;

    private Object newData;

    private Object oldData;

}
