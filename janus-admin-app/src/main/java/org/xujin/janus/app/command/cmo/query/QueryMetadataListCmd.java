package org.xujin.janus.app.command.cmo.query;

import org.xujin.halo.dto.Command;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class QueryMetadataListCmd extends Command {

    @ApiModelProperty(value = "搜索关键字", required = false)
    private List<String> searchKeys;
}


