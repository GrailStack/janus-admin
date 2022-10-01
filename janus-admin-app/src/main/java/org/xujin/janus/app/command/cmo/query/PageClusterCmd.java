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
@ApiModel("集群分页查询参数")
public class PageClusterCmd extends PageQueryCommand {
    private String name;
    private String bizName;
    private String userName;
    private String filter;
    private String keyWord;
}
