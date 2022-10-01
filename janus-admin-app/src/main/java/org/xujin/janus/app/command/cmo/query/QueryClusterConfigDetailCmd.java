package org.xujin.janus.app.command.cmo.query;

import org.xujin.janus.app.command.cmo.CommonCommand;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/5/27 10:32
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询集群配置详情")
public class QueryClusterConfigDetailCmd extends CommonCommand {
    private BigInteger id;
}
