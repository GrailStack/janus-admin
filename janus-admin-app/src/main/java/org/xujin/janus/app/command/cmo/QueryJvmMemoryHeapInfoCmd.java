package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/6/10 17:54
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询JvmMemoryUsed")
public class QueryJvmMemoryHeapInfoCmd extends CommonCommand {
    @ApiModelProperty("集群code")
    private String clusterCode;

    @ApiModelProperty("主机host")
    private String host;

    @ApiModelProperty("jvm heap:  传heap        jvm Non-heap: 传 nonheap")
    private String area;
}
