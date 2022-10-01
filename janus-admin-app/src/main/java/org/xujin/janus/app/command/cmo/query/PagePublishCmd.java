package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.PageQueryCommand;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
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
@ApiModel("下发分页查询参数")
public class PagePublishCmd extends PageQueryCommand {

    private String clusterCode;

    private ReleaseTypeEnum type;

    private String creator;

}
