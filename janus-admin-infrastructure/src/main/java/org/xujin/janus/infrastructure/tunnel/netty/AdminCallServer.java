package org.xujin.janus.infrastructure.tunnel.netty;

import org.xujin.janus.damon.JanusCmdClient;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *  Admin call Server
 * @author xujin
 */
@Component
@Slf4j
public class AdminCallServer {

    public void sendMsg(JanusCmdMsg janusCmdMsg,String remoteAddress) {
        JanusCmdClient janusCmdClient = JanusCmdClient.getSingleton();
        try {
            janusCmdClient.asyncSend(remoteAddress,janusCmdMsg);
        } catch (Exception e) {
            log.error("janus admin call server fail:{}", e.getStackTrace());
        }

    }


}
