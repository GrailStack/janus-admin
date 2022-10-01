package org.xujin.janus.domain.change.collect;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 20:20
 **/
@Data
@Accessors(chain = true)
public class ChangeInfoRequest implements Serializable {

    private String clusterCode;

}
