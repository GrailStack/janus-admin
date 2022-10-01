package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.PagePublishCmd;
import org.xujin.janus.app.command.co.PublishDetailCO;
import org.xujin.janus.app.converter.PublishClientConverter;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.domain.user.service.PublishService;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishIpMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishIpDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/15 13:48
 * @desc
 */
@Slf4j
@CmdHandler
public class PagePublishCmdExe implements CommandExecutorI<ResultData<PageResult<PublishDetailCO>>, PagePublishCmd> {

    @Resource
    private PublishClientConverter publishClientConverter;

    @Resource
    private PublishMapper publishMapper;

    @Autowired
    private PublishIpMapper publishIpMapper;

    @Autowired
    private PublishService publishService;

    @Autowired
    private ClusterService clusterService;

    @Override
    public ResultData<PageResult<PublishDetailCO>> execute(PagePublishCmd cmd) {
        IPage<PublishDO> page = page(cmd);
        PageResult<PublishDetailCO> pageResult = new PageResult<>();
        List<PublishDetailCO> coList = publishClientConverter.dataToClients(page.getRecords());

        clusterService.fillClusterNameByCode(coList, PublishDetailCO::getClusterCode, PublishDetailCO::setClusterName);

        fillDetail(coList);

        pageResult.setList(coList);
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        return ResultData.success(pageResult);
    }

    public IPage<PublishDO> page(PagePublishCmd cmd) {
        Page<PublishDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());

        QueryWrapper<PublishDO> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(cmd.getClusterCode())) {
            queryWrapper.eq("cluster_code", cmd.getClusterCode());
        }
        if (!StringUtils.isEmpty(cmd.getCreator())) {
            queryWrapper.like("creator", cmd.getCreator());
        }
        if (cmd.getType() != null) {
            queryWrapper.eq("type", cmd.getType().getCode());
        }
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.orderByDesc("id");
        return publishMapper.selectPage(page, queryWrapper);
    }

    private void fillDetail(List<PublishDetailCO> coList) {
        if (CollectionUtils.isEmpty(coList)) {
            return;
        }
        List<Long> publishIdList = coList.stream().map(PublishDetailCO::getId).collect(Collectors.toList());
        List<PublishIpDO> publishIpDOS = publishIpMapper.batchFindByPublishId(publishIdList);
        Map<Long, List<PublishIpDO>> publishId2ipDoList = publishIpDOS.stream().collect(Collectors.groupingBy(PublishIpDO::getPublishId));
        for (PublishDetailCO co : coList) {
            List<PublishIpDO> ipDOList = publishId2ipDoList.get(co.getId());
            fillCompletionRate(co, ipDOList);
        }
    }

    private void fillCompletionRate(PublishDetailCO co, List<PublishIpDO> ipDOList) {
        int totalCount = 0;
        int completedCount = 0;
        int completionRate = 0;
        if (CollectionUtils.isNotEmpty(ipDOList)) {
            for (PublishIpDO publishIpDO : ipDOList) {
                totalCount++;
                if (publishService.isLogicalCompletedStatus(publishIpDO.getStatus())) {
                    completedCount++;
                }
            }
            completionRate = completedCount * 100 / totalCount;
        }
        co.setTotalCount(totalCount);
        co.setCompletedCount(completedCount);
        co.setCompletionRate(completionRate);
    }


}
