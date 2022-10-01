package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.CommonCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xujin
 */
@Data
public class PageQueryRoleCmd extends CommonCommand {

    @ApiModelProperty(value = "页号", required = true)
    private Integer pageNo = 1;

    @ApiModelProperty(value = "页大小", required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "搜索关键字", required = false)
    private String searchKey;


}
