package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.app.command.cmo.query.*;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.app.command.co.JanusInstanceCO;
import org.xujin.janus.infrastructure.ClusterDetailCo;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import org.xujin.janus.infrastructure.utils.UserUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:40
 * @desc
 */
@RestController
@RequestMapping("/admin/cluster")
@Api(tags = "cluster-controller")
public class ClusterController {

    @Resource
    private CommandBus commandBus;

    @PostMapping("/add")
    @ApiOperation(value = "新增集群")
    public ResultData add(@RequestBody AddClusterCmd addClusterCmd) {
        addClusterCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(addClusterCmd);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除集群")
    public ResultData delete(@RequestBody DeleteClusterCmd deleteClusterCmd) {
        deleteClusterCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(deleteClusterCmd);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新集群")
    public ResultData update(@RequestBody UpdateClusterCmd updateClusterCmd) {
        updateClusterCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(updateClusterCmd);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询集群详情")
    public ResultData<ClusterDetailCo> detail(@ModelAttribute QueryClusterDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/page")
    @ApiOperation(value = "查询集群分页")
    public ResultData<PageResult<ClusterDetailCo>> page(@ModelAttribute PageClusterCmd pageClusterCmd) {
        if (StringUtils.isEmpty(pageClusterCmd.getUserName())) {
            pageClusterCmd.setUserName(UserUtil.getCurrUser());
        }
        return commandBus.send(pageClusterCmd);
    }

    @PostMapping("/addClusterFilter")
    @ApiOperation(value = "新增集群与Filter的绑定")
    public ResultData addClusterFilter(@RequestBody AddClusterFilterCmd addClusterFilterCmd) {
        addClusterFilterCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(addClusterFilterCmd);
    }

    @PostMapping("/updateClusterFilter")
    @ApiOperation(value = "更新集群与Filter的绑定")
    public ResultData updateClusterFilter(@RequestBody UpdateClusterFilterCmd updateClusterFilterCmd) {
        updateClusterFilterCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(updateClusterFilterCmd);
    }

    @GetMapping("/userClusters")
    @ApiOperation(value = "当前用户所有集群")
    public ResultData<ClusterDetailCo> queryUserAllCluster() {
        return commandBus.send(new QueryUserClustersCmd());
    }

    @PostMapping("/clusterInstances")
    @ApiOperation(value = "查询集群实例")
    public ResultData<List<JanusInstanceCO>> queryClusterInstances(@RequestBody @Valid QueryClusterInstancesCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/countCluster")
    @ApiOperation(value = "当前集群数量")
    public ResultData<Integer> countCluster() {
        return commandBus.send(new QueryClusterCountCmd());
    }


    @GetMapping("/listFiltersByClusterCode")
    @ApiOperation(value = "根据clusterCode查询filter信息")
    public ResultData<List<FilterDetailCo>> listFiltersByClusterCode(@ModelAttribute QueryClusterFilterDetailCmd cmd) {
        return commandBus.send(cmd);
    }

}
