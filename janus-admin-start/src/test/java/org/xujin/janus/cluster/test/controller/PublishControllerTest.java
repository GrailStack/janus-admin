package org.xujin.janus.cluster.test.controller;

import org.xujin.janus.app.command.cmo.AddPublishCmd;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.PublishController;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/27 14:47
 * @desc
 */
public class PublishControllerTest extends CommonTest {

    @Resource
    private PublishController publishController;

    @Test
    public void testAdd() {
        AddPublishCmd addPublishCmd = new AddPublishCmd();
        addPublishCmd.setClusterCode("clusterCode");
        addPublishCmd.setApplyId(new BigInteger("1"));
        addPublishCmd.setPublisher("任成龙");
        List<String> ips = new ArrayList<>();
        ips.add("192.168.1.1");
        addPublishCmd.setIps(ips);
        publishController.add(addPublishCmd);
    }



}
