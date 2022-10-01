package org.xujin.janus.domain.change.submit;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/9 15:08
 **/
@Data
@Accessors(chain = true)
public class SubmitChangeRequest implements Serializable {

    private Long applyId;

    private String clusterCode;

    private String description;

    private String sourceCode;

    private List<Long> resourceIdList;

}
