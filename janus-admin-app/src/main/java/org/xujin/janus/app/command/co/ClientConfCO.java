package org.xujin.janus.app.command.co;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenglong.ren
 * @date 2020/5/27 11:22
 * @desc
 */
@Data
public class ClientConfCO implements Serializable {
    private String remoteAddr;
    private Long port;
    private Boolean keepalive;
    private Boolean reUseAddr;
    private Boolean tcpNoDelay;
    private Long backLog;
    private Long sndBuf;
    private Long revBuf;
    private Long heart;
    private Long workThread;
}
