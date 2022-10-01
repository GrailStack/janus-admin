package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/12 16:07
 **/
@Data
public class ChangeReleaseCmd extends Command {

    @ApiModelProperty("变更申请ID")
    @NotNull
    @Min(1)
    private Long applyId;

    @ApiModelProperty("发布类型")
    @NotNull(message = "发布类型不正确")
    private ReleaseTypeEnum type;

    @ApiModelProperty("ip列表")
    @NotEmpty(message = "ip列表不正确")
    private List<String> ipList;

    @ApiModelProperty("选中ip列表")
    @NotEmpty(message = "选中ip列表不正确")
    private List<String> selectedIpList;

}
