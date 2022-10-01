package org.xujin.janus.app.command;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.AddFilterCmd;
import org.xujin.janus.app.converter.FilterClientConverter;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.repository.FilterRepository;
import org.xujin.janus.domain.user.service.FilterService;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.FilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:12
 * @desc
 */
@CmdHandler
public class AddFilterCmdExe implements CommandExecutorI<ResultData, AddFilterCmd> {

    @Resource
    private FilterClientConverter converter;

    @Resource
    private FilterRepository filterRepository;

    @Resource
    private FilterService filterService;

    @Resource
    private FilterMapper filterMapper;


    @Override
    public ResultData execute(AddFilterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        QueryWrapper<FilterDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("filter_code", cmd.getFilterCode());
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_TRUE);
        List<FilterDO> filterDOS = filterMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(filterDOS)) {
            throw new BusinessException("400", "filterCode不能重复");
        }
        FilterE filterE = converter.addCmdToEntity(cmd);
        if (!StringUtils.isEmpty(cmd.getModelType())) {
            filterE.setIsShare(new Byte(cmd.getModelType()));
        }
        filterE.setRepository(filterRepository);
        filterService.addFilter(filterE);
        return resultData;
    }

    protected boolean check(AddFilterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getFilterCode(), "filterCode不能为null");
        return true;
    }
}
