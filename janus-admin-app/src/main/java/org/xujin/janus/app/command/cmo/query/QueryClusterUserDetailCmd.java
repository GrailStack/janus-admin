package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.CommonCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:55
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询用户与集群绑定")
public class QueryClusterUserDetailCmd extends CommonCommand {
    @ApiModelProperty("id")
    private BigInteger id;
}
