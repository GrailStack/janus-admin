package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.CommonCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chenglong.ren
 */
@Data
public class PageQueryClusterUserCmd extends CommonCommand {

    @ApiModelProperty(value = "页号", required = true)
    private Integer pageNo = 1;

    @ApiModelProperty(value = "页大小", required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "用户名", required = false)
    private String userName;

    @ApiModelProperty(value = "集群编号", required = false)
    private String clusterCode;
}
