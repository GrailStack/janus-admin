package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import lombok.Data;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/17 15:30
 **/
@Data
public class ServerPullDownCmd extends Command {

    private Long publishIpId;

}
