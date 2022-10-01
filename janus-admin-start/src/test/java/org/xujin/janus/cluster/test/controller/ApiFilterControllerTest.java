package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddApiFilterCmd;
import org.xujin.janus.app.command.cmo.AddApiFilterDetailCmd;
import org.xujin.janus.app.command.cmo.UpdateApiFilterCmd;
import org.xujin.janus.app.command.cmo.UpdateApiFilterDetailCmd;
import org.xujin.janus.app.command.cmo.query.ApiFilterDetailCmd;
import org.xujin.janus.app.command.co.ApiFilterDetailCO;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.ApiFilterController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/6/17 13:35
 * @desc
 */
public class ApiFilterControllerTest extends CommonTest {

    @Autowired
    private ApiFilterController filterController;

    @Test
    public void testAdd() {
        AddApiFilterCmd addApiFilterCmd = new AddApiFilterCmd();
        List<AddApiFilterDetailCmd> filters = new ArrayList<>();
        AddApiFilterDetailCmd addApiFilterDetailCmd = new AddApiFilterDetailCmd();
        addApiFilterDetailCmd.setApiId("1");
        addApiFilterDetailCmd.setClusterCode("clusterCode");
        addApiFilterDetailCmd.setFilterCode("filterCode");
        addApiFilterDetailCmd.setFilterId("1");
        addApiFilterDetailCmd.setFilterName("filterName");
        addApiFilterDetailCmd.setFilterParam("param");


        AddApiFilterDetailCmd addApiFilterDetailCmd1 = new AddApiFilterDetailCmd();
        addApiFilterDetailCmd1.setApiId("2");
        addApiFilterDetailCmd1.setClusterCode("clusterCode1");
        addApiFilterDetailCmd1.setFilterCode("filterCode1");
        addApiFilterDetailCmd1.setFilterId("2");
        addApiFilterDetailCmd1.setFilterName("filterName1");
        addApiFilterDetailCmd1.setFilterParam("param1");



        filters.add(addApiFilterDetailCmd);
        filters.add(addApiFilterDetailCmd1);
        addApiFilterCmd.setFilters(filters);
        filterController.add(addApiFilterCmd);
    }


    @Test
    public void testUpdate() {
        UpdateApiFilterCmd updateApiFilterCmd = new UpdateApiFilterCmd();
        List<UpdateApiFilterDetailCmd> filters = new ArrayList<>();
        UpdateApiFilterDetailCmd addApiFilterDetailCmd = new UpdateApiFilterDetailCmd();
        addApiFilterDetailCmd.setApiId("1");
        addApiFilterDetailCmd.setClusterCode("clusterCode111");
        addApiFilterDetailCmd.setFilterCode("filterCode1");
        addApiFilterDetailCmd.setFilterId("1");
        addApiFilterDetailCmd.setFilterName("filterName1");
        addApiFilterDetailCmd.setFilterParam("param1");
        addApiFilterDetailCmd.setId(1);


        UpdateApiFilterDetailCmd addApiFilterDetailCmd1 = new UpdateApiFilterDetailCmd();
        addApiFilterDetailCmd1.setApiId("2");
        addApiFilterDetailCmd1.setClusterCode("clusterCode222");
        addApiFilterDetailCmd1.setFilterCode("filterCode2");
        addApiFilterDetailCmd1.setFilterId("2");
        addApiFilterDetailCmd1.setFilterName("filterName2");
        addApiFilterDetailCmd1.setFilterParam("param2");
        addApiFilterDetailCmd1.setId(2);



        filters.add(addApiFilterDetailCmd);
        filters.add(addApiFilterDetailCmd1);
        updateApiFilterCmd.setFilters(filters);
        filterController.update(updateApiFilterCmd);
    }

    @Test
    public void testDetail() {
        ApiFilterDetailCmd cmd = new ApiFilterDetailCmd();
        cmd.setId(new Long("889"));
        ResultData<ApiFilterDetailCO> detail = filterController.detail(cmd);
        System.out.println(detail);
    }
}
