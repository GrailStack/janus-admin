package org.xujin.janus.domain.change.audit;

import org.xujin.janus.domain.change.constant.AuditActionEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 16:48
 **/
@Data
@Accessors(chain = true)
public class AuditChangesRequest {

    private Long applyId;

    private AuditActionEnum action;

}
