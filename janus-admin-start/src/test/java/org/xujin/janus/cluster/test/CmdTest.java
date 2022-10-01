package org.xujin.janus.cluster.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.xujin.janus.damon.exchange.NettyMsg;
import org.xujin.janus.damon.serializer.AbstractSerializer;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:38
 * @desc
 */
@Slf4j
public class CmdTest  {

    private final AbstractSerializer serializer = AbstractSerializer.SerializeEnum.PROTOSTUFF.getSerializer();

    @Test
    public void cmdTest() {

        JanusCmdMsg cmdMsg = JanusCmdMsg.builder()
                .version("123")
                .cluster("test")
                .payload("11111")
                .method("test222")
                .buildRequest();
        NettyMsg nettyMsg = new NettyMsg();
        nettyMsg.setCmdMsg(cmdMsg);
        byte[] bs = serializer.serialize(nettyMsg);
        NettyMsg result = (NettyMsg) serializer.deserialize(bs, NettyMsg.class);
        log.info("hello!!! : " + result);
    }

    public static void main(String[] args) {
        Long a=241658839040L;
        System.out.println(a/1024/1024/1024);
    }


}
