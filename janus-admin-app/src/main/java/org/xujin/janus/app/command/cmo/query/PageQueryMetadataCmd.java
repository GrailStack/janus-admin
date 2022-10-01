package org.xujin.janus.app.command.cmo.query;

import org.xujin.halo.dto.Command;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageQueryMetadataCmd extends Command {

    @ApiModelProperty(value = "页号", required = true)
    private Integer pageNo = 1;

    @ApiModelProperty(value = "页大小", required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "搜索关键字", required = false)
    private String searchKey;
}


