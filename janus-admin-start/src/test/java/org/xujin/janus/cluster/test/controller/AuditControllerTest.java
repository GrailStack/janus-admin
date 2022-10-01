package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.DeleteAuditLogCmd;
import org.xujin.janus.app.command.cmo.query.PageAuditLogCmd;
import org.xujin.janus.app.command.co.AuditLogDetailCO;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.AuditLogController;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author chenglong.ren
 * @date 2020/6/22 17:49
 * @desc
 */
public class AuditControllerTest extends CommonTest {

    @Resource
    private AuditLogController auditLogController;

    @Test
    public void testList() {
        PageAuditLogCmd pageAuditLogCmd = new PageAuditLogCmd();
        pageAuditLogCmd.setPageNo(1);
        pageAuditLogCmd.setPageSize(10);
        pageAuditLogCmd.setStartTime("2020-06-08 11:34:19");
        pageAuditLogCmd.setEndTime("2020-06-08 11:44:19");
        ResultData<PageResult<AuditLogDetailCO>> page = auditLogController.page(pageAuditLogCmd);
        System.out.println(page);
    }

    @Test
    public void testDelete() {
        DeleteAuditLogCmd deleteAuditLogCmd = new DeleteAuditLogCmd();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(12);
        ids.add(13);
        ids.add(14);
        deleteAuditLogCmd.setIds(ids);
        ResultData delete = auditLogController.delete(deleteAuditLogCmd);
        System.out.println(delete);
    }
}
