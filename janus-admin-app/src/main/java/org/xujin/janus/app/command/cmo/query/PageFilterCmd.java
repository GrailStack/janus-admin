package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.PageQueryCommand;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/4/22 16:24
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("filter分页查询参数")
public class PageFilterCmd extends PageQueryCommand {
    private String filterName;
    private String userName;
    private String classCode;

    private String classType;
    private String status;

    private String filterCode;
}