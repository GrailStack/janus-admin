package org.xujin.janus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xujin.halo.command.CommandBus;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.*;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.app.command.cmo.query.PageQueryMetadataCmd;
import org.xujin.janus.app.command.cmo.query.QueryMetadataListCmd;
import org.xujin.janus.app.command.cmo.query.QueryMultiDictCmd;
import org.xujin.janus.app.command.co.DictDataCO;
import org.xujin.janus.app.command.co.DictTypeListCO;
import org.xujin.janus.app.command.query.PageQueryMetadataCmdExe;
import org.xujin.janus.app.command.query.QueryMetadataListCmdExe;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 元数据Controller
 *
 * @author xujin
 */
@RestController
@Api("元数据管理")
@RequestMapping("/admin/metadata")
public class MetaDataController {

    @Autowired
    private CommandBus commandBus;

    @PostMapping("/page")
    @ApiOperation(value = "分页查询元数据")
    @RequiresPermissions("metadata:query")
    ResultData<PageResult<DictTypeListCO>> queryDictByPage(@RequestBody PageQueryMetadataCmd cmd) {
        return commandBus.send(cmd, PageQueryMetadataCmdExe.class);
    }

    @PostMapping("/list")
    @ApiOperation(value = "全量查询元数据")
    @RequiresPermissions("metadata:query")
    ResultData<PageResult<Map<String, List<DictTypeListCO>>>> list(@RequestBody QueryMetadataListCmd cmd) {
        return commandBus.send(cmd, QueryMetadataListCmdExe.class);
    }

    /**
     * 增加数据字典类型
     *
     * @param cmd
     * @return
     */
    @PostMapping("/addDictType")
    @ApiOperation(value = "增加数据字典类型")
    @RequiresPermissions("metadata:dictType:add")
    public ResultData addDictType(@RequestBody AddDictTypeCmd cmd) {
        return commandBus.send(cmd, AddDictTypeCmdExe.class);
    }

    /**
     * 更新数据字典类型
     *
     * @param cmd
     * @return
     */
    @PostMapping("/updateDictType")
    @ApiOperation(value = "修改数据字典类型")
    @RequiresPermissions("metadata:dictType:update")
    public ResultData updateDictType(@RequestBody UpdateDictTypeCmd cmd) {
        return commandBus.send(cmd, UpdateDictTypeCmdExe.class);
    }

    /**
     * 删除数据字典类型
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除数据字典类型")
    @RequiresPermissions("metadata:dictType:update")
    @GetMapping("/deleteDictType/{id}")
    public ResultData<Void> deleteDictType(@PathVariable Long id) {
        DeleteDictTypeCmd cmd = new DeleteDictTypeCmd();
        cmd.setId(id);
        return commandBus.send(cmd);

    }

    /**
     * 增加数据字典项
     *
     * @param cmd
     * @return
     */
    @ApiOperation(value = "增加数据字典项")
    @RequiresPermissions("metadata:dictData:add")
    @PostMapping("/addDictData")
    public ResultData addDictData(@RequestBody AddDictDataCmd cmd) {
        return commandBus.send(cmd, AddDictDataCmdExe.class);
    }

    /**
     * 更新数据字典项
     *
     * @param cmd
     * @return
     */
    @ApiOperation(value = "更新数据字典项")
    @RequiresPermissions("metadata:dictData:update")
    @PostMapping("/updateDictData")
    public ResultData updateDictData(@RequestBody UpdateDictDataCmd cmd) {
        return commandBus.send(cmd, UpdateDictDataCmdExe.class);
    }

    /**
     * 删除数据字典项
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除数据字典项")
    @RequiresPermissions("metadata:dictData:delete")
    @GetMapping("/deleteDictData/{id}")
    public ResultData deleteDictData(@PathVariable Long id) {
        DeleteDictDataCmd cmd = new DeleteDictDataCmd();
        cmd.setId(id);
        return commandBus.send(cmd, DeleteDictDataCmdExe.class);
    }

    @PostMapping("/queryMultiDict")
    @RequiresPermissions("metadata:query")
    public ResultData<Map<String, List<DictDataCO>>> queryMultiDict(@RequestBody @Valid QueryMultiDictCmd cmd) {
        return commandBus.send(cmd);
    }

}
