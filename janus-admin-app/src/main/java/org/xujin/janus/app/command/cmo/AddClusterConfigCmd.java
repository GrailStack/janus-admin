package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/5/27 9:58
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("新增集群配置参数")
public class AddClusterConfigCmd  extends CommonCommand {
    private String clusterCode;
    private String configKey;
    private String configValue;
    private String name;
    private String type;
    private Byte status;
}
