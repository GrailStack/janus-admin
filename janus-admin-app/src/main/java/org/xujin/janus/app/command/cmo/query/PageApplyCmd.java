package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.PageQueryCommand;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/4/15 13:47
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("审批分页查询参数")
public class PageApplyCmd extends PageQueryCommand {

    private String clusterCode;

    private String searchKey;

}
