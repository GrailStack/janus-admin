package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:08
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("新增filter参数")
public class AddFilterCmd extends CommonCommand {

    private String name;

    private Byte isGlobal;

    private Byte isShare;

    private String filterArgs;

    private Integer filterOrder;

    private String clusterCode;

    private String creator;

    private String classCode;

    private String description;
    private String status;

    private String filterCode;
    private String executePlace;

    private String modelType;

}
