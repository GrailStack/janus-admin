package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.DeleteFilterCmd;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.domain.user.entity.ClusterEntity;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.service.FilterService;
import org.xujin.janus.infrastructure.converter.ClusterFilterConverter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:09
 * @desc
 */
@Slf4j
@CmdHandler
public class DeleteFilterCmdExe implements CommandExecutorI<ResultData, DeleteFilterCmd> {

    @Resource
    private FilterClientConverter converter;

    @Resource
    private FilterService filterService;

    @Override
    public ResultData execute(DeleteFilterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        if (filterService.findById(cmd.getId()) == null) {
            throw new BusinessException("400", "没有查询到相关数据");
        }
        FilterE filterE = converter.deleteCmdToEntity(cmd);
        Byte isDelete = 1;
        filterE.setIsDeleted(isDelete);
        filterService.deleteFilter(filterE);
        return resultData;
    }
    protected boolean check(DeleteFilterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId() != null, "集群id不能为空");
        return true;
    }
}
