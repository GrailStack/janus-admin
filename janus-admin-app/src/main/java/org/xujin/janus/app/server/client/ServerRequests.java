package org.xujin.janus.app.server.client;

import org.xujin.janus.client.cmo.*;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import lombok.extern.slf4j.Slf4j;


/**
 * @author: ganshitao
 * @date: 2020/5/22
 */
@Slf4j
public class ServerRequests extends AbstractRequest {
    /**
     * 异步发送基础配置变更
     *
     * @param address
     * @param cluster
     * @param version
     * @param requestID
     * @param configChangeCmd
     * @throws Exception
     */
    public static void sendConfigChanged(String address, String cluster, String version, String requestID, ConfigChangeCmd configChangeCmd) throws Exception {
        asyncSend(address, cluster, version, ConfigChangeCmd.method, requestID, configChangeCmd);
    }

    /**
     * 异步发送路由变更
     *
     * @param address
     * @param cluster
     * @param version
     * @param requestID
     * @param routeChangeCmd
     * @throws Exception
     */
    public static void sendRouteChanged(String address, String cluster, String version, String requestID, RouteChangeCmd routeChangeCmd) throws Exception {
        asyncSend(address, cluster, version, RouteChangeCmd.method, requestID, routeChangeCmd);
    }

    /**
     * 异步发送新的类文件
     *
     * @param address
     * @param cluster
     * @param version
     * @param requestID
     * @param sendFileCmd
     * @throws Exception
     */
    public static void sendNewFile(String address, String cluster, String version, String requestID, SendFileCmd sendFileCmd) throws Exception {
        asyncSend(address, cluster, version, SendFileCmd.method, requestID, sendFileCmd);
    }

    public static JanusCmdMsg sendOnlineChanged(String address, String cluster, String version, ChangeOnlineCmd changeOnlineCmd) throws Exception {
        return syncSend(address, cluster, version, ChangeOnlineCmd.method, changeOnlineCmd);
    }

    public void sendCallBack(String address, String cluster, String version, String requestID, String requestMethod, int processResult, String errorMessage) {
        CallBackCmd callBackCmd = new CallBackCmd();
        callBackCmd.setRequestID(requestID);
        callBackCmd.setRequestMethod(requestMethod);
        callBackCmd.setProcessResult(processResult);
        callBackCmd.setErrorMessage(errorMessage);
        try {
            asyncSend(address, cluster, version, CallBackCmd.method, null, callBackCmd);
        } catch (Exception e) {
            log.error("send call back to admin fail:{}", e.getMessage());
        }
    }

}
