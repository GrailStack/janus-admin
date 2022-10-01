package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.CommonCommand;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/6/10 17:54
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询DiskFree")
public class QueryDiskFreeCmdCmd extends CommonCommand {
    private String clusterCode;
    private String ip;

}
