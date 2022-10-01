package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PageAuditLogCmd;
import org.xujin.janus.app.command.co.AuditLogDetailCO;
import org.xujin.janus.app.converter.AuditLogClientConverter;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApplyMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.AuditLogMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AuditLogDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:43
 * @desc
 */

@Slf4j
@CmdHandler
public class PageQueryAuditLogCmdExe implements CommandExecutorI<ResultData<PageResult<AuditLogDetailCO>>, PageAuditLogCmd> {

    @Autowired
    private AuditLogClientConverter converter;

    @Autowired
    private AuditLogMapper auditLogMapper;

    @Override
    public ResultData<PageResult<AuditLogDetailCO>> execute(PageAuditLogCmd cmd) {
        ResultData<PageResult<AuditLogDetailCO>> resultData = ResultData.success(null);
        check(cmd);
        IPage<AuditLogDO> page = page(cmd);
        PageResult<AuditLogDetailCO> pageResult = new PageResult<>();
        pageResult.setList(converter.dataToClients(page.getRecords()));
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        resultData.setData(pageResult);
        return resultData;
    }

    protected boolean check(PageAuditLogCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        return true;
    }

    public IPage<AuditLogDO> page(PageAuditLogCmd cmd) {
        Page<AuditLogDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<AuditLogDO> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(cmd.getUserName())) {
            queryWrapper.eq("username", cmd.getUserName());
        }
        if (!StringUtils.isEmpty(cmd.getType())) {
            queryWrapper.eq("type", cmd.getType());
        }
        if (null != cmd.getStartTime() && null != cmd.getEndTime()) {
            queryWrapper.between("start_time", cmd.getStartTime(), cmd.getEndTime());
        }
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        queryWrapper.orderByDesc("gmt_create");
        return auditLogMapper.selectPage(page, queryWrapper);
    }
}

