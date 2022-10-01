package org.xujin.janus.app.command;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.UpdateApiFilterCmd;
import org.xujin.janus.app.converter.ApiFilterClientConverter;
import org.xujin.janus.domain.user.entity.ApiFilterE;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiFilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiFilterDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/6/17 11:49
 * @desc
 */
@CmdHandler
public class UpdateApiFilterCmdExe implements CommandExecutorI<ResultData, UpdateApiFilterCmd> {

    @Resource
    private ApiFilterClientConverter converter;

    @Resource
    private ApiFilterMapper apiFilterMapper;

    @Override
    public ResultData execute(UpdateApiFilterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        List<ApiFilterE> apiFilterEs = converter.updateCmdToEntity(cmd);
        String apiId = cmd.getFilters().get(0).getApiId();
        QueryWrapper<ApiFilterDO> wrapper = new QueryWrapper<>();
        wrapper.eq("api_id", apiId);
        apiFilterMapper.delete(wrapper);
        apiFilterEs.forEach(ApiFilterE::save);
        return resultData;
    }

    protected boolean check(UpdateApiFilterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getFilters(), "cmd不能为null");
        return true;
    }
}
