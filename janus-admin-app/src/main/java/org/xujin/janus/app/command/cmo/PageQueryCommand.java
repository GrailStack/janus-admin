package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:44
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("分页查询")
public class PageQueryCommand extends CommonCommand {
    @ApiModelProperty(value = "页号", required = true)
    @Positive(message = "pageNo不正确")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "页大小", required = true)
    @Min(value = 1, message = "pageSize不正确")
    @Max(value = 500, message = "pageSize不正确")
    private Integer pageSize = 10;

}
