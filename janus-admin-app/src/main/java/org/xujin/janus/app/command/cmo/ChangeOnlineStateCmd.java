package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import lombok.Data;

/**
 * @author chenglong.ren
 * @date 2020/6/12 14:22
 * @desc
 */
@Data
public class ChangeOnlineStateCmd extends Command {
    private boolean online;
    private String clusterCode;
    private String ip;
    private Integer port;
}
