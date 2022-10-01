package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.PageQueryCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xujin
 */
@Data
public class PageClusterConfigCmd  extends PageQueryCommand {
    private String clusterCode;
    private String configKey;
}
