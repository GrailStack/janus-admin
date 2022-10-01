package org.xujin.janus.app.command.cmo;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/4/22 18:11
 * @desc
 */
@Data
public class DeleteClusterUserCmd extends CommonCommand {
    private BigInteger id;
}
