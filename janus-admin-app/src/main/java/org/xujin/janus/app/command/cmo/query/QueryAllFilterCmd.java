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
 * @date 2020/4/22 15:55
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询所有filter详情")
public class QueryAllFilterCmd extends CommonCommand {
}
