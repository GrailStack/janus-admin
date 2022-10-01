package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:53
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("新增下发参数")
public class AddPublishCmd extends CommonCommand {
    /**
     * 下发申请的Id
     */
    private BigInteger applyId;

    /**
     * 下发类型，0：灰度；1：全量
     */
    private Byte type;

    /**
     * 下发人
     */
    private String publisher;

    /**
     * 下发状态 0 下发中  1 忽略 2 下发完成
     */
    private Short status;

    /**
     * 集群编码
     */
    private String clusterCode;

    /**
     * 灰度传ip    全量传list
     */
    private List<String> ips;


}
