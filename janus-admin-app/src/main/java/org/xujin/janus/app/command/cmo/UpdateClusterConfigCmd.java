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
@ApiModel("更新集群配置参数")
public class UpdateClusterConfigCmd extends CommonCommand {
    @ApiModelProperty("审批id")
    private BigInteger id;

    private String clusterCode;
    private String configKey;
    private String configValue;
    private String name;
    private String type;
    private Byte status;
}


