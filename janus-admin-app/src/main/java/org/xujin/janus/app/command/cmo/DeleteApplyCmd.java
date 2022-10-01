package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/4/15 10:40
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("删除审批参数")
public class DeleteApplyCmd extends CommonCommand {
    private BigInteger id;
}
