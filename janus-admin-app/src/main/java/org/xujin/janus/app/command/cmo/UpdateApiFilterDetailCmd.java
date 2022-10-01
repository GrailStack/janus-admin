package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:53
 * @desc
 */
@Data
@ApiModel("新增api-filter参数")
public class UpdateApiFilterDetailCmd {
    private Integer id;
    private String apiId;

    private String clusterCode;
    /**
     * 该表逻辑主键
     */
    private String filterId;

    /**
     * Filter中文名
     */
    private String filterName;

    /**
     * Filter类名
     */
    private String filterCode;

    /**
     * filter参数,json类型
     */

    private String filterParam;

}
