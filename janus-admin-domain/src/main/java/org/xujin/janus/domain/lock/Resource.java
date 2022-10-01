package org.xujin.janus.domain.lock;

import org.xujin.janus.domain.lock.constant.ResourceTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/10 13:46
 **/
@Data
@Accessors(chain = true)
public class Resource implements Serializable {

    private ResourceTypeEnum resourceType;

    private String resourceId;

    private String operation;

}
