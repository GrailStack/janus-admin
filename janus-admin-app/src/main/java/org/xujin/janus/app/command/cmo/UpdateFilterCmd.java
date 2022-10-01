package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:32
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("更新Filter参数")
public class UpdateFilterCmd extends CommonCommand {

    private BigInteger id;

    private String name;

    private Byte isGlobal;

    private Byte isShare;

    private String filterArgs;

    private Integer filterOrder;

    private String modifier;

    private String classCode;

    private String executePlace;

    private String modelType;

    private String description;
    private String status;

    private String filterCode;
}
