package org.xujin.janus.damon;

import com.alibaba.fastjson.JSON;
import org.xujin.halo.shiro.util.SpringContextUtils;
import org.xujin.janus.app.command.cmo.query.QueryFilterDetailCmd;
import org.xujin.janus.app.server.processer.AlarmProcessor;
import org.xujin.janus.app.server.processer.HeartBeatProcessor;
import org.xujin.janus.app.server.processer.QueryAllConfigProcessor;
import org.xujin.janus.app.server.processer.ServerCallBackProcessor;
import org.xujin.janus.client.cmo.AlarmCmd;
import org.xujin.janus.client.cmo.CallBackCmd;
import org.xujin.janus.client.cmo.HeartBeatCmd;
import org.xujin.janus.client.cmo.QueryAllConfigCmd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JanusAdminDamonServer {

    public static void start(int port) {
        log.info("<----------start janus admin damon server------> ");
        JanusCmdServer janusCmdServer = new JanusCmdServer();
        Map processerMap = new HashMap(2);
        log.info("<----------init processor map------------------> ");
        QueryAllConfigProcessor queryAllConfigProcessor = SpringContextUtils.getBean(QueryAllConfigProcessor.class);
        HeartBeatProcessor heartBeatProcessor = SpringContextUtils.getBean(HeartBeatProcessor.class);
        ServerCallBackProcessor serverCallBackProcessor = SpringContextUtils.getBean(ServerCallBackProcessor.class);
        AlarmProcessor alarmProcessor = SpringContextUtils.getBean(AlarmProcessor.class);
        processerMap.put(HeartBeatCmd.method, heartBeatProcessor);
        processerMap.put(QueryAllConfigCmd.method, queryAllConfigProcessor);

        //Server接收到下发配置之后,回Call Admin更新数据库中的下发状态
        processerMap.put(CallBackCmd.method, serverCallBackProcessor);
        processerMap.put(AlarmCmd.method, alarmProcessor);
        log.info("damon server port:{},processorMap:{}", port, JSON.toJSONString(processerMap));
        janusCmdServer.init(port, processerMap);
        janusCmdServer.start();
        log.info("<----------janus admin damon server end!------>");
    }
}
