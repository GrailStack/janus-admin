package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.InstanceDetailCmd;
import org.xujin.janus.app.command.cmo.query.PageInstanceCmd;
import org.xujin.janus.app.command.co.InstanceDetailCO;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.InstanceController;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:38
 * @desc
 */
public class InstanceControllerTest extends CommonTest {

    @Resource
    private InstanceController instanceController;

    @Test
    public void testDetail() {
        InstanceDetailCmd cmd = new InstanceDetailCmd();
        cmd.setId(new Long("1"));
        ResultData<InstanceDetailCO> detail = instanceController.detail(cmd);
        System.out.println(detail);
    }

    @Test
    public void testPage() {
        PageInstanceCmd pageInstanceCmd = new PageInstanceCmd();
        pageInstanceCmd.setClusterCode("Janus_Test");
        ResultData<PageResult<InstanceDetailCO>> page = instanceController.page(pageInstanceCmd);
        System.out.println(page);
    }

    @Test
    public void testCount() {
        ResultData<Integer> integerResultData = instanceController.countInstance();
        System.out.println(integerResultData);
    }
}
