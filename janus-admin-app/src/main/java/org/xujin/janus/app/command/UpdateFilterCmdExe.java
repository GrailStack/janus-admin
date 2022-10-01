package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.UpdateFilterCmd;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.service.FilterService;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:33
 * @desc
 */
@Slf4j
@CmdHandler
public class UpdateFilterCmdExe implements CommandExecutorI<ResultData, UpdateFilterCmd> {

    @Resource
    private FilterService filterService;

    @Resource
    private FilterClientConverter converter;

    @Resource
    private FilterMapper filterMapper;

    @Override
    public ResultData execute(UpdateFilterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        FilterDO filterDO = filterMapper.selectById(cmd.getId());
        if (null == filterDO) {
            throw new BusinessException("400", "不存在该filter");
        }
        FilterE filterE = converter.updateCmdToEntity(cmd);
        if (!StringUtils.isEmpty(cmd.getModelType())) {
            filterE.setIsShare(new Byte(cmd.getModelType()));
        }
        filterService.updateFilter(filterE);
        return resultData;
    }

    protected boolean check(UpdateFilterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"集群id不能为空");
        return true;
    }
}
