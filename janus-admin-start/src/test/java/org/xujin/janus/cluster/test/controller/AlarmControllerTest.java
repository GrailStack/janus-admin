package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddFilterCmd;
import org.xujin.janus.app.command.cmo.DeleteFilterCmd;
import org.xujin.janus.app.command.cmo.UpdateAlarmCmd;
import org.xujin.janus.app.command.cmo.UpdateFilterCmd;
import org.xujin.janus.app.command.cmo.query.AlarmDetailCmd;
import org.xujin.janus.app.command.cmo.query.PageAlarmCmd;
import org.xujin.janus.app.command.cmo.query.PageFilterCmd;
import org.xujin.janus.app.command.cmo.query.QueryFilterDetailCmd;
import org.xujin.janus.app.command.co.AlarmDetailCO;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.AlarmController;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:38
 * @desc
 */
public class AlarmControllerTest extends CommonTest {

    @Resource
    private AlarmController alarmController;


    @Test
    public void testUpdate() {
        UpdateAlarmCmd updateAlarmCmd = new UpdateAlarmCmd();
        updateAlarmCmd.setId(new Long("1"));
        updateAlarmCmd.setStatus(new Byte("1"));
        alarmController.update(updateAlarmCmd);
    }

    @Test
    public void testDetail() {
        AlarmDetailCmd cmd = new AlarmDetailCmd();
        cmd.setId(new Long("1"));
        ResultData<AlarmDetailCO> detail = alarmController.detail(cmd);
        System.out.println(detail);
    }


    @Test
    public void testPage() {
        PageAlarmCmd pageAlarmCmd = new PageAlarmCmd();
        pageAlarmCmd.setSort("gmt_create");
        ResultData<PageResult<AlarmDetailCO>> page = alarmController.page(pageAlarmCmd);
        System.out.println(page);
    }

}
