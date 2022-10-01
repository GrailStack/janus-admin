package org.xujin.janus.app.command.cmo.query;

import org.xujin.halo.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/15 16:43
 **/
@Data
public class QueryClusterInstancesCmd extends Command {

    @NotEmpty
    private String clusterCode;

}
