package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:43
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("新增集群参数")
public class AddClusterCmd extends CommonCommand {
    @ApiModelProperty("集群编码")
    private String code;

    @ApiModelProperty("集群中文名称")
    private String name;

    @ApiModelProperty("负责人姓名")
    private String ownerName;

    @ApiModelProperty("业务名称")
    private String bizName;

    @ApiModelProperty("负责人Id")
    private String ownerId;

    @ApiModelProperty("集群描述")
    private String description;
    private String domainName;

    @ApiModelProperty("是否共享集群")
    private Byte isShare;

    private String state;

}
