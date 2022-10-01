package org.xujin.janus.app.server.processer;

import org.springframework.beans.factory.annotation.Autowired;
import org.xujin.halo.annotation.app.AppService;
import org.xujin.janus.app.server.callback.RouteChangeCallBack;
import org.xujin.janus.client.cmo.CallBackCmd;
import org.xujin.janus.client.cmo.ConfigChangeCmd;
import org.xujin.janus.client.cmo.RouteChangeCmd;
import org.xujin.janus.client.cmo.SendFileCmd;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: gan
 * @date: 2020/6/12
 */
@Slf4j
@AppService
public class ServerCallBackProcessor extends AbstractProcessor {


    @Autowired
    private RouteChangeCallBack routeChangeCallBack;

    @Override
    public JanusCmdMsg doExecute(JanusCmdMsg msg) {
        CallBackCmd callBackCmd = (CallBackCmd) msg.getPayload();
        String requestMethod = callBackCmd.getRequestMethod();
        String requestID = callBackCmd.getRequestMethod();
        // 0 成功  非0 失败
        int processResult = callBackCmd.getProcessResult();
        String errorMessage = callBackCmd.getErrorMessage();
        switch (requestMethod) {
            case RouteChangeCmd.method:
                //TODO
                log.info("receive routeChange callBack");
               // routeChangeCallBack.execute()
                return successResponse(msg, null);
            case SendFileCmd.method:
                //TODO
                log.info("receive sendFile callBack");
                return successResponse(msg, null);
            case ConfigChangeCmd.method:
                //TODO
                log.info("receive configChange callBack");
                return successResponse(msg, null);
            default:
                return successResponse(msg, null);

        }
    }
}
