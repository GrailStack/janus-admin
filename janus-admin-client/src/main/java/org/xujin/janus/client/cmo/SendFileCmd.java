package org.xujin.janus.client.cmo;

import java.util.List;

/**
 * @author: ganshitao
 * @date: 2020/5/25
 */
public class SendFileCmd {
    public static final String method = "sendFile";
    private List<SendFileDTO> sendFileDTOS;

    public List<SendFileDTO> getSendFileDTOS() {
        return sendFileDTOS;
    }

    public void setSendFileDTOS(List<SendFileDTO> sendFileDTOS) {
        this.sendFileDTOS = sendFileDTOS;
    }
}