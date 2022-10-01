package org.xujin.janus.app.command;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.ApiDraftPageQueryCmd;
import org.xujin.janus.app.command.co.ApiDraftCO;
import org.xujin.janus.app.converter.ApiClientConverter;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.domain.user.service.UserService;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiDraftMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiDraftDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/8 15:48
 **/
@CmdHandler
public class ApiDraftPageQueryCmdExe implements CommandExecutorI<ResultData<PageResult<ApiDraftCO>>, ApiDraftPageQueryCmd> {

    @Autowired
    private ApiDraftMapper apiDraftMapper;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiClientConverter converter;

    @Override
    public ResultData<PageResult<ApiDraftCO>> execute(ApiDraftPageQueryCmd cmd) {
        IPage<ApiDraftDO> page = pageGetApiDraft(cmd);
        PageResult<ApiDraftCO> pageResult = new PageResult<>();
        pageResult.setList(convert(page.getRecords()));
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        return ResultData.success(pageResult);
    }

    private IPage<ApiDraftDO> pageGetApiDraft(ApiDraftPageQueryCmd cmd) {
        Page<ApiDraftDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<ApiDraftDO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(cmd.getClusterCode())) {
            queryWrapper.eq("cluster_code", cmd.getClusterCode());
        }
        if (StringUtils.isNoneBlank(cmd.getSearchKey())) {
            queryWrapper.and(qw -> qw
                    .like("name", cmd.getSearchKey())
                    .or()
                    .like("path", cmd.getSearchKey())
            );
        }
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByDesc("id");
        return apiDraftMapper.selectPage(page, queryWrapper);
    }

    private List<ApiDraftCO> convert(List<ApiDraftDO> draftDOList) {
        if (CollectionUtils.isEmpty(draftDOList)) {
            return Lists.newArrayList();
        }
        List<ApiDraftCO> coList = converter.convertToApiDraftCOList(draftDOList);

        clusterService.fillClusterNameByCode(coList, ApiDraftCO::getClusterCode, ApiDraftCO::setClusterName);

        List<String> userNames = Lists.newArrayList(Lists.transform(coList, ApiDraftCO::getCreateUser));
        userNames.addAll(Lists.transform(coList, ApiDraftCO::getUpdateUser));
        Map<String, String> userDisplayNames = userService.getNames(userNames);

        coList.forEach(co -> {
            co.setCreateUserName(userDisplayNames.get(co.getCreateUser()));
            co.setUpdateUserName(userDisplayNames.get(co.getUpdateUser()));
        });
        return coList;
    }

}
