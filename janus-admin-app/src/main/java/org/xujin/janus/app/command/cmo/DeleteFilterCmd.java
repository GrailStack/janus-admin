package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:08
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("删除filter参数")
public class DeleteFilterCmd extends CommonCommand {
    private BigInteger id;

    private Byte isGlobal;
}
