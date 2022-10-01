package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.CommonCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author chenglong.ren
 * @date 2020/4/15 11:08
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询集群详情")
public class QueryClusterFilterDetailCmd extends CommonCommand {
    @ApiModelProperty("集群code")
    private String clusterCode;
}
