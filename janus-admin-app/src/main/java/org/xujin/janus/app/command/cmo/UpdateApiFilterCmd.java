package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:53
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("更新api-filter参数")
public class UpdateApiFilterCmd extends CommonCommand {
    private List<UpdateApiFilterDetailCmd> filters;
}
