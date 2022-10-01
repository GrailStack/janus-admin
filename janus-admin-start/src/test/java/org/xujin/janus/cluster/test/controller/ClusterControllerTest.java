package org.xujin.janus.cluster.test.controller;

import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddClusterCmd;
import org.xujin.janus.app.command.cmo.AddClusterFilterCmd;
import org.xujin.janus.app.command.cmo.DeleteClusterCmd;
import org.xujin.janus.app.command.cmo.UpdateClusterCmd;
import org.xujin.janus.app.command.cmo.query.PageClusterCmd;
import org.xujin.janus.app.command.cmo.query.QueryClusterDetailCmd;
import org.xujin.janus.app.command.cmo.query.QueryClusterFilterDetailCmd;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.ClusterController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/14 19:03
 * @desc
 */
@Slf4j
public class ClusterControllerTest extends CommonTest {

    @Resource
    ClusterController clusterController;

    @Test
    public void testAdd() {
        AddClusterCmd addClusterCmd = new AddClusterCmd();
        addClusterCmd.setCode("code");
        addClusterCmd.setName("测试");
        addClusterCmd.setOwnerName("任成龙");
        addClusterCmd.setBizName("业务名");
        addClusterCmd.setDescription("描述");
        addClusterCmd.setIsShare(new Byte("1"));
        addClusterCmd.setOwnerId("ownerId");
        ResultData add = clusterController.add(addClusterCmd);
        System.out.println(add);
    }

    @Test
    public void testDelete() {
        DeleteClusterCmd deleteClusterCmd = new DeleteClusterCmd();
        deleteClusterCmd.setId(new BigInteger("2"));
        clusterController.delete(deleteClusterCmd);
    }

    @Test
    public void testUpdate() {
        UpdateClusterCmd updateClusterCmd = new UpdateClusterCmd();
        updateClusterCmd.setId(new BigInteger("2"));
        updateClusterCmd.setBizName("测试11111");
        updateClusterCmd.setIsShare(new Byte("1"));
        clusterController.update(updateClusterCmd);
    }

    @Test
    public void testDetail() {
        QueryClusterDetailCmd cmd = new QueryClusterDetailCmd();
        cmd.setId(new BigInteger("20"));
        ResultData<ClusterDetailCo> detail = clusterController.detail(cmd);
        ClusterDetailCo data = detail.getData();
        System.out.println(data);
    }

    @Test
    public void testPage() {
        PageClusterCmd cmd = new PageClusterCmd();
        cmd.setName("测试");
        cmd.setUserName("baifa.ma");
        ResultData<PageResult<ClusterDetailCo>> page = clusterController.page(cmd);
        PageResult<ClusterDetailCo> data = page.getData();
        System.out.println(data);
    }


    @Test
    public void testAddClusterFilter() {
        AddClusterFilterCmd addClusterFilterCmd = new AddClusterFilterCmd();
        addClusterFilterCmd.setCode("testCode");
        ArrayList<BigInteger> filterIds = new ArrayList<>();
        addClusterFilterCmd.setFilterIds(filterIds);
        clusterController.addClusterFilter(addClusterFilterCmd);
    }

    @Test
    public void testCount() {
        ResultData<Integer> integerResultData = clusterController.countCluster();
        System.out.println(integerResultData);
    }

    @Test
    public void testFilters() {
        QueryClusterFilterDetailCmd cmd = new QueryClusterFilterDetailCmd();
        cmd.setClusterCode("JANUS_ORDER");
        ResultData<List<FilterDetailCo>> listResultData = clusterController.listFiltersByClusterCode(cmd);
        System.out.println(listResultData);
    }
}
