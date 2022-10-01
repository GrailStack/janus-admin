package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:43
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("新增集群与Filter绑定参数")
public class AddClusterFilterCmd extends CommonCommand {
    @ApiModelProperty("集群编码")
    private String code;

    private List<BigInteger> filterIds;
}
