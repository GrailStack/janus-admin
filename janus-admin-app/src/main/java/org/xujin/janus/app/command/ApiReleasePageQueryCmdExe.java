package org.xujin.janus.app.command;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.ApiReleasePageQueryCmd;
import org.xujin.janus.app.command.co.ApiReleaseCO;
import org.xujin.janus.app.converter.ApiClientConverter;
import org.xujin.janus.domain.user.service.ClusterService;
import org.xujin.janus.domain.user.service.UserService;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 12:03
 **/
@CmdHandler
public class ApiReleasePageQueryCmdExe implements CommandExecutorI<ResultData<PageResult<ApiReleaseCO>>, ApiReleasePageQueryCmd> {

    @Autowired
    private ApiReleaseMapper apiReleaseMapper;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiClientConverter converter;

    @Override
    public ResultData<PageResult<ApiReleaseCO>> execute(ApiReleasePageQueryCmd cmd) {
        IPage<ApiReleaseDO> page = pageGetApi(cmd);
        PageResult<ApiReleaseCO> pageResult = new PageResult<>();
        pageResult.setList(convert(page.getRecords()));
        pageResult.setCurrentPage(page.getCurrent());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        return ResultData.success(pageResult);
    }

    private IPage<ApiReleaseDO> pageGetApi(ApiReleasePageQueryCmd cmd) {
        Page<ApiReleaseDO> page = new Page<>(cmd.getPageNo(), cmd.getPageSize());
        QueryWrapper<ApiReleaseDO> queryWrapper = new QueryWrapper<>();
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
        queryWrapper.orderByDesc("id");
        return apiReleaseMapper.selectPage(page, queryWrapper);
    }

    private List<ApiReleaseCO> convert(List<ApiReleaseDO> releaseDOList) {
        if (CollectionUtils.isEmpty(releaseDOList)) {
            return Lists.newArrayList();
        }

        List<ApiReleaseCO> coList = converter.convertToApiReleaseCOList(releaseDOList);

        clusterService.fillClusterNameByCode(coList, ApiReleaseCO::getClusterCode, ApiReleaseCO::setClusterName);

        List<String> userNames = Lists.newArrayList(Lists.transform(coList, ApiReleaseCO::getCreateUser));
        userNames.addAll(Lists.transform(coList, ApiReleaseCO::getUpdateUser));
        Map<String, String> userDisplayNames = userService.getNames(userNames);

        coList.forEach(co -> {
            co.setCreateUserName(userDisplayNames.get(co.getCreateUser()));
            co.setUpdateUserName(userDisplayNames.get(co.getUpdateUser()));
        });

        return coList;
    }

}
