package org.xujin.janus.app.server.client;

import org.xujin.janus.damon.JanusCmdClient;
import org.xujin.janus.damon.exchange.JanusCmdMsg;

/**
 * @author: gan
 * @date: 2020/6/12
 */
public abstract class AbstractRequest {
    /**
     * 同步发送接口
     *
     * @param address
     * @param cluster
     * @param version
     * @param method
     * @param payload
     * @return
     * @throws Exception
     */
    public static JanusCmdMsg syncSend(String address, String cluster, String version, String method, Object payload) throws Exception {
        JanusCmdMsg janusCmdMsg = JanusCmdMsg.builder()
                .cluster(cluster)
                .version(version)
                .method(method)
                .payload(payload)
                .needCallBack(false)
                .buildRequest();
        JanusCmdClient janusCmdClient = JanusCmdClient.getSingleton();
        JanusCmdMsg response = janusCmdClient.syncSend(address, janusCmdMsg);
        return response;
    }

    /**
     * 异步接口
     * requestID 为null不发送异步回调；否则，发送异步回调
     * @param method
     * @param payload
     * @throws Exception
     */
    public static void asyncSend(String address, String cluster, String version, String method, String requestID, Object payload) throws Exception {
        boolean needCallBack = true;
        if (requestID == null) {
            needCallBack = false;
        }
        JanusCmdMsg janusCmdMsg = JanusCmdMsg.builder()
                .cluster(cluster)
                .version(version)
                .method(method)
                .payload(payload)
                .requestID(requestID)
                .needCallBack(needCallBack)
                .buildRequest();
        JanusCmdClient janusCmdClient = JanusCmdClient.getSingleton();
        janusCmdClient.asyncSend(address, janusCmdMsg);

    }
}
