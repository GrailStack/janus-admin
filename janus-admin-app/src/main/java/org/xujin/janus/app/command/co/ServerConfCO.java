package org.xujin.janus.app.command.co;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenglong.ren
 * @date 2020/5/27 11:13
 * @desc
 */
@Data
public class ServerConfCO implements Serializable {
    private String serverName;
    private Boolean keepalive;
    private Boolean reUseAddr;
    private Boolean tcpNoDelay;
    private Long backLog;
    private Long sndBuf;
    private Long revBuf;
    private Long heart;
    private Long bossThread;
    private Long workThread;
    private Long maxInitialLineLength;
    private Long maxHeaderSize;
    private Long maxChunkSize;
    private Long maxRequestBufferSizeInBytes;
    private Long maxHttpLength;
}
