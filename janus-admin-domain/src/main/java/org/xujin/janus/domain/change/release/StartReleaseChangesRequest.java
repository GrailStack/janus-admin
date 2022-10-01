package org.xujin.janus.domain.change.release;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/19 20:16
 **/
@Data
@Accessors(chain = true)
public class StartReleaseChangesRequest {

    private Long applyId;

}
