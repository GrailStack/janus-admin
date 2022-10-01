package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.CommonCommand;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author chenglong.ren
 * @date 2020/6/10 17:54
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询qps")
public class QueryQpsCmd extends CommonCommand {
    private String clusterCode;
}
