package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:58
 **/
@Data
public class ApiReleasePageQueryCmd extends Command {

    @ApiModelProperty(value = "页号", required = true)
    @Positive(message = "pageNo exceed the limit")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "页大小", required = true)
    @Min(value = 1, message = "pageSize exceed the limit")
    @Max(value = 200, message = "pageSize exceed the limit")
    private Integer pageSize = 20;

    @ApiModelProperty(value = "集群编号", required = false)
    private String clusterCode;

    @ApiModelProperty(value = "搜索关键字", required = false)
    private String searchKey;

}
