package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("更新集群参数")
public class UpdateClusterCmd  extends CommonCommand {
    @ApiModelProperty("集群id")
    private BigInteger id;

    @ApiModelProperty("集群编码")
    private String code;

    @ApiModelProperty("集群中文名称")
    private String name;

    private String domainName;

    @ApiModelProperty("负责人姓名")
    private String ownerName;

    private String state;

    @ApiModelProperty("业务名称")
    private String bizName;

    @ApiModelProperty("负责人Id")
    private String ownerId;

    @ApiModelProperty("集群描述")
    private String description;

    @ApiModelProperty("是否共享集群")
    private Byte isShare;
}


