package org.xujin.janus.app.command.cmo.query;

import org.xujin.halo.dto.Command;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/4/22 16:24
 * @desc
 */
@Data
@ApiModel("查询所有filter参数")
public class QueryAllFiltersCmd extends Command {
    private String classCode;
}