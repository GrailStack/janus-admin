package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddApplyCmd;
import org.xujin.janus.app.command.cmo.DeleteApplyCmd;
import org.xujin.janus.app.command.cmo.query.PageApplyCmd;
import org.xujin.janus.app.command.cmo.query.QueryApplyDetailCmd;
import org.xujin.janus.app.command.co.ApplyDetailCO;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.ApplyController;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * @author chenglong.ren
 * @date 2020/5/26 16:40
 * @desc
 */
public class ApplyControllerTest extends CommonTest {

    @Resource
    private ApplyController applyController;

    @Test
    public void testAdd() {
        AddApplyCmd addApplyCmd = new AddApplyCmd();
        addApplyCmd.setClusterCode("clusterCode");
        addApplyCmd.setDescription("描述");
        addApplyCmd.setChanges("改变的内容");
        addApplyCmd.setPublisher("发布人");
        addApplyCmd.setApprover("审批人");
        addApplyCmd.setCurrentUserId("发起人");
        applyController.add(addApplyCmd);
    }

    @Test
    public void testDetail() {
        QueryApplyDetailCmd cmd = new QueryApplyDetailCmd();
        cmd.setId(new BigInteger("2"));
        ResultData<ApplyDetailCO> detail = applyController.detail(cmd);
        System.out.println(detail);
    }

    @Test
    public void testPage() {
        PageApplyCmd pageApplyCmd = new PageApplyCmd();
        pageApplyCmd.setClusterCode("clusterCode");
        ResultData<PageResult<ApplyDetailCO>> page = applyController.page(pageApplyCmd);
        System.out.println(page);
    }

    @Test
    public void testDelete() {
        DeleteApplyCmd deleteApplyCmd = new DeleteApplyCmd();
        deleteApplyCmd.setId(new BigInteger("5"));
        ResultData delete = applyController.delete(deleteApplyCmd);

    }


}
