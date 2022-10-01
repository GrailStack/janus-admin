package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddFilterCmd;
import org.xujin.janus.app.command.cmo.DeleteFilterCmd;
import org.xujin.janus.app.command.cmo.UpdateFilterCmd;
import org.xujin.janus.app.command.cmo.query.PageFilterCmd;
import org.xujin.janus.app.command.cmo.query.QueryAllFiltersCmd;
import org.xujin.janus.app.command.cmo.query.QueryFilterDetailCmd;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.FilterController;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:38
 * @desc
 */
public class FilterControllerTest extends CommonTest {

    @Resource
    private FilterController filterController;

    @Test
    public void testAdd() {
        AddFilterCmd addFilterCmd = new AddFilterCmd();
        addFilterCmd.setName("filterName");
        addFilterCmd.setIsGlobal(new Byte("1"));
        addFilterCmd.setIsShare(new Byte("0"));
        addFilterCmd.setFilterArgs("test args");
        addFilterCmd.setClusterCode("cluster Code");
        addFilterCmd.setCreator("chenglong.ren Test");
        filterController.add(addFilterCmd);
    }

    @Test
    public void testDelete() {
        DeleteFilterCmd cmd = new DeleteFilterCmd();
        cmd.setId(new BigInteger("2"));
        cmd.setIsGlobal(new Byte("1"));
        filterController.delete(cmd);
    }

    @Test
    public void testUpdate() {
        UpdateFilterCmd updateFilterCmd = new UpdateFilterCmd();
        updateFilterCmd.setId(new BigInteger("2"));
        updateFilterCmd.setModifier("任成龙");
        updateFilterCmd.setName("test Update");
        filterController.update(updateFilterCmd);
    }

    @Test
    public void testDetail() {
        QueryFilterDetailCmd queryFilterDetailCmd = new QueryFilterDetailCmd();
        queryFilterDetailCmd.setId(new BigInteger("2"));
        ResultData<FilterDetailCo> detail = filterController.detail(queryFilterDetailCmd);
        System.out.println(detail);
    }

    @Test
    public void testPage() {
        PageFilterCmd pageFilterCmd = new PageFilterCmd();
        pageFilterCmd.setUserName("baifa.ma");
        ResultData<PageResult<FilterDetailCo>> page = filterController.page(pageFilterCmd);

        PageFilterCmd pageFilterCmd1 = new PageFilterCmd();
        pageFilterCmd1.setUserName("chenglong.ren");
        ResultData<PageResult<FilterDetailCo>> page1 = filterController.page(pageFilterCmd1);
        System.out.println(page);
    }

    @Test
    public void testListFilters() {
        ResultData<FilterDetailCo> allFilterCodes = filterController.getAllFilterCodes();
        System.out.println(allFilterCodes);
    }

    @Test
    public void testListAll() {
        QueryAllFiltersCmd queryAllFiltersCmd = new QueryAllFiltersCmd();
        queryAllFiltersCmd.setClassCode("pre_in");
        ResultData<List<FilterDetailCo>> listResultData = filterController.listFilters(queryAllFiltersCmd);
        System.out.println(listResultData);
    }
}
