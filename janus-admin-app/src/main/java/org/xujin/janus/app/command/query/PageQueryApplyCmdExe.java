package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PageApplyCmd;
import org.xujin.janus.app.command.co.ApplyDetailCO;
import org.xujin.janus.app.converter.ApplyClientConverter;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApplyMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 17:43
 * @desc
 */

@Slf4j
@CmdHandler
public class PageQueryApplyCmdExe implements CommandExecutorI<ResultData<PageResult<ApplyDetailCO>>, PageApplyCmd> {

    @Autowired
    private ApplyClientConverter converter;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ClusterService clusterService;

    @Override
    public ResultData<PageResult<ApplyDetailCO>> execute(PageApplyCmd cmd) {
        IPage<ApplyDO> page = page(cmd);
        PageResult<ApplyDetailCO> pageResult = new PageResult<>();
        pageResult.setList(convert(page.getRecords()));
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        return ResultData.success(pageResult);
    }


    private IPage<ApplyDO> page(PageApplyCmd cmd) {
        Page<ApplyDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<ApplyDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        if (StringUtils.isNotBlank(cmd.getClusterCode())) {
            queryWrapper.eq("cluster_code", cmd.getClusterCode());
        }
        if (StringUtils.isNotBlank(cmd.getSearchKey())) {
            queryWrapper.and(qw -> qw
                    .like("creator", cmd.getSearchKey())
                    .or()
                    .like("approver", cmd.getSearchKey())
                    .or()
                    .like("publisher", cmd.getSearchKey())
            );
        }
        queryWrapper.orderByDesc("id");
        return applyMapper.selectPage(page, queryWrapper);
    }

    private List<ApplyDetailCO> convert(List<ApplyDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
            return Lists.newArrayList();
        }
        List<ApplyDetailCO> coList = converter.dataToClients(doList);
        clusterService.fillClusterNameByCode(coList, ApplyDetailCO::getClusterCode, ApplyDetailCO::setClusterName);
        return coList;
    }

}

