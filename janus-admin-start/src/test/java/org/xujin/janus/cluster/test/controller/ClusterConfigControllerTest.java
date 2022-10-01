package org.xujin.janus.cluster.test.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xujin.halo.context.Context;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.extension.ExtensionExecutor;
import org.xujin.janus.app.command.cmo.AddClusterConfigCmd;
import org.xujin.janus.app.command.cmo.DeleteClusterConfigCmd;
import org.xujin.janus.app.command.cmo.UpdateClusterConfigCmd;
import org.xujin.janus.app.command.cmo.query.PageClusterConfigCmd;
import org.xujin.janus.app.command.cmo.query.QueryClusterConfigDetailCmd;
import org.xujin.janus.app.command.co.ClusterConfigDetailCO;
import org.xujin.janus.app.command.co.JanusInstanceCO;
import org.xujin.janus.app.extension.extpt.JanusInstanceExtPt;
import org.xujin.janus.app.server.processer.QueryAllConfigProcessor;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.ClusterConfigController;
import org.xujin.janus.domain.user.service.FilterService;
import org.yaml.snakeyaml.Yaml;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenglong.ren
 * @date 2020/5/27 10:11
 * @desc
 */
public class ClusterConfigControllerTest extends CommonTest {
    @Autowired
    private ClusterConfigController clusterConfigController;
    @Autowired
    private ExtensionExecutor extensionExecutor;
    @Autowired
    private QueryAllConfigProcessor queryAllConfigProcessor;
    @Test
    public void testET() {
        Context context = new Context();
        context.setBizCode("janus.instance.source.db");
        List<JanusInstanceCO> janusInstanceCOS = extensionExecutor.exeReturnValue(JanusInstanceExtPt.class, context, extension -> extension.queryJanusInstanceByClusterCode("rclCode"));
        System.out.println(janusInstanceCOS);
    }

    @Test
    public void testAdd() {
        AddClusterConfigCmd addClusterConfigCmd = new AddClusterConfigCmd();
        addClusterConfigCmd.setClusterCode("clusterCode");
        addClusterConfigCmd.setConfigKey("key");
        addClusterConfigCmd.setConfigValue("value");
        addClusterConfigCmd.setName("集群配置名称");
        addClusterConfigCmd.setType("配置类型");
        addClusterConfigCmd.setStatus(new Byte("0"));
        ResultData<Void> add = clusterConfigController.add(addClusterConfigCmd);
    }

    @Test
    public void testUpdate() {
        UpdateClusterConfigCmd updateClusterConfigCmd = new UpdateClusterConfigCmd();
        updateClusterConfigCmd.setClusterCode("clusterCode");
        updateClusterConfigCmd.setConfigKey("key");
        updateClusterConfigCmd.setConfigValue("value1");
        updateClusterConfigCmd.setId(new BigInteger("1"));
        ResultData<Void> update = clusterConfigController.update(updateClusterConfigCmd);
    }

    @Test
    public void testDelete() {
        DeleteClusterConfigCmd deleteClusterConfigCmd = new DeleteClusterConfigCmd();
        deleteClusterConfigCmd.setId(new BigInteger("1"));
        ResultData<Void> update = clusterConfigController.delete(deleteClusterConfigCmd);
    }

    @Test
    public void testDetail() {
        QueryClusterConfigDetailCmd queryClusterConfigDetailCmd = new QueryClusterConfigDetailCmd();
        queryClusterConfigDetailCmd.setId(new BigInteger("6"));
        ResultData<ClusterConfigDetailCO> detail = clusterConfigController.detail(queryClusterConfigDetailCmd);
        System.out.println(detail);
    }

    @Autowired
    private FilterService filterService;
    @Test
    public void testPage() {
        String s = "serverConf:\n" +
                "  keepalive: true #Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能\n" +
                "  reUseAddr: true #地址复用，默认值False。有四种情况可以使用：(1).当有一个有相同本地地址和端口的socket1处于TIME_WAIT状态时，而你希望启动的程序的socket2要占用该地址和端口，比如重启服务且保持先前端口。(2).有多块网卡或用IP Alias技术的机器在同一端口启动多个进程，但每个进程绑定的本地IP地址不能相同。(3).单个进程绑定相同的端口到多个socket上，但每个socket绑定的ip地址不同。(4).完全相同的地址和端口的重复绑定。但这只用于UDP的多播，不用于TCP。\n" +
                "  tcpNoDelay: true #TCP参数，立即发送数据，默认值为Ture（Netty默认为True而操作系统默认为False）。该值设置Nagle算法的启用，改算法将小的碎片数据连接成更大的报文来最小化所发送的报文的数量，如果需要发送一些较小的报文，则需要禁用该算法。Netty默认禁用该算法，从而最小化报文传输延时。\n" +
                "  backLog: 1024 #  Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝\n" +
                "  sndBuf: 10485760 # Socket参数，TCP数据发送缓冲区大小。\n" +
                "  revBuf: 10485760 # Socket参数，TCP数据接收缓冲区大小。\n" +
                "  heart: 180 # 读超时时间\n" +
                "  bossThread: 2\n" +
                "  workThread: 4\n" +
                "\n" +
                "  maxInitialLineLength: 8192 # The maximum length of the initial line (e.g. \"GET / HTTP/1.0\") If the length of the initial line exceeds this value, a TooLongFrameException will be raised.\n" +
                "  maxHeaderSize: 16384 # \tThe maximum length of all headers.\n" +
                "  maxChunkSize: 18384 #    \tThe maximum length of the content or each chunk. If the content length exceeds this value, the transfer encoding of the decoded request will be converted to 'chunked' and the content will be split into multiple HttpContents. If the transfer encoding of the HTTP request is 'chunked' already, each chunk will be split into smaller chunks if the length of the chunk exceeds this value. If you prefer not to handle HttpContents in your handler, insert HttpObjectAggregator after this decoder in the ChannelPipeline.\n" +
                "  maxHttpLength: 65535 #    最大Http请求长度\n" +
                "\n" +
                "clientConf:\n" +
                "  keepalive: true #Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能\n" +
                "  reUseAddr: true #地址复用，默认值False。有四种情况可以使用：(1).当有一个有相同本地地址和端口的socket1处于TIME_WAIT状态时，而你希望启动的程序的socket2要占用该地址和端口，比如重启服务且保持先前端口。(2).有多块网卡或用IP Alias技术的机器在同一端口启动多个进程，但每个进程绑定的本地IP地址不能相同。(3).单个进程绑定相同的端口到多个socket上，但每个socket绑定的ip地址不同。(4).完全相同的地址和端口的重复绑定。但这只用于UDP的多播，不用于TCP。\n" +
                "  tcpNoDelay: true #TCP参数，立即发送数据，默认值为true（Netty默认为True而操作系统默认为False）。该值设置Nagle算法的启用，改算法将小的碎片数据连接成更大的报文来最小化所发送的报文的数量，如果需要发送一些较小的报文，则需要禁用该算法。Netty默认禁用该算法，从而最小化报文传输延时。\n" +
                "  backLog: 1024 #  Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝\n" +
                "  sndBuf: 10485760 # Socket参数，TCP数据发送缓冲区大小。\n" +
                "  revBuf: 10485760 # Socket参数，TCP数据接收缓冲区大小。\n" +
                "  heart: 180 # 读超时时间\n" +
                "  workThread: 4\n" +
                "\n" +
                "dynamicClass:\n" +
                "  filtersPath: /opt/apps/conf/janus/plugin/filters  #filters\n" +
                "  predicatesPath: /opt/apps/conf/janus/plugin/predicates  #predicates\n" +
                "  pollingIntervalSeconds: 300 # 拉取文件时间隔间\n" +
                "  compileFileThreads: 4 # 处理java、groovy线程个数\n" +
                "  compileFileThreadTimeOut: 30 # 处理java、groovy 线程任务超时时间 单位秒\n" +
                "\n" +
                "globalFilters:\n" +
                "  - CircuitBreaker\n" +
                "  - Retry\n" +
                "janusInfluxConfig:\n" +
                "  uri: http://10.81.53.251:8086\n" +
                "  userName: admin\n" +
                "  db: janus";

        Yaml yaml = new Yaml();
        String dump = yaml.dump(yaml.load(s));
        System.out.println(dump);



        Map map = (Map) yaml.load(s);

        System.out.println(map);

        Map map1 = new HashMap();
        PageClusterConfigCmd pageClusterConfigCmd = new PageClusterConfigCmd();
        pageClusterConfigCmd.setClusterCode("clusterCode");
        ResultData<PageResult<ClusterConfigDetailCO>> page = clusterConfigController.page(pageClusterConfigCmd);
        List<ClusterConfigDetailCO> list = page.getData().getList();

        for (int i = 0; i < list.size(); i++) {
            ClusterConfigDetailCO x = list.get(i);
            map1.put(x.getConfigKey(), x.getConfigValue());
        }
        List<String> clusterCode = filterService.globalFilters("clusterCode");
        map1.put("globalFilters", clusterCode.toArray(new String[0]));
        String dump1 = yaml.dump(map1);
        System.out.println(map1);
    }
}
