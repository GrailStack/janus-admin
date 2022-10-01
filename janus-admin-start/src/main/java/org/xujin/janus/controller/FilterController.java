package org.xujin.janus.controller;

import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddFilterCmd;
import org.xujin.janus.app.command.cmo.DeleteFilterCmd;
import org.xujin.janus.app.command.cmo.UpdateFilterCmd;
import org.xujin.janus.app.command.cmo.query.PageFilterCmd;
import org.xujin.janus.app.command.cmo.query.QueryAllFilterCmd;
import org.xujin.janus.app.command.cmo.query.QueryAllFiltersCmd;
import org.xujin.janus.app.command.cmo.query.QueryFilterDetailCmd;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import org.xujin.janus.infrastructure.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:07
 * @desc
 */
@RestController
@RequestMapping("/admin/filter")
@Api("filter管理")
public class FilterController {
    @Resource
    private CommandBus commandBus;

    @PostMapping("/add")
    @ApiOperation(value = "新增filter")
    public ResultData add(@RequestBody AddFilterCmd addFilterCmd) {
        addFilterCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(addFilterCmd);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除filter")
    public ResultData delete(@RequestBody DeleteFilterCmd deleteFilterCmd) {
        deleteFilterCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(deleteFilterCmd);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新filter")
    public ResultData update(@RequestBody UpdateFilterCmd updateFilterCmd) {
        updateFilterCmd.setCurrentUserId(SessionUtils.getUserName());
        return commandBus.send(updateFilterCmd);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "查询filter详情")
    public ResultData<FilterDetailCo> detail(@ModelAttribute QueryFilterDetailCmd cmd) {
        return commandBus.send(cmd);
    }

    @GetMapping("/page")
    @ApiOperation(value = "查询filter分页")
    public ResultData<PageResult<FilterDetailCo>> page(@ModelAttribute PageFilterCmd pageFilterCmd) {
        if (StringUtils.isEmpty(pageFilterCmd.getUserName())) {
            pageFilterCmd.setUserName(UserUtil.getCurrUser());
        }
        return commandBus.send(pageFilterCmd);
    }

    @GetMapping("/listFilters")
    @ApiOperation(value = "查询所有filter")
    public ResultData<List<FilterDetailCo>> listFilters(@ModelAttribute QueryAllFiltersCmd queryAllFiltersCmd) {
        return commandBus.send(queryAllFiltersCmd);
    }

    @GetMapping("/getAllFilterCodes")
    @ApiOperation(value = "查询所有的filterCode")
    public ResultData<FilterDetailCo> getAllFilterCodes() {
        QueryAllFilterCmd cmd = new QueryAllFilterCmd();
        return commandBus.send(cmd);
    }

}
