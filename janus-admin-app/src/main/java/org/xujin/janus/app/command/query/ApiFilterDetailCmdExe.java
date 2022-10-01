package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.ApiFilterDetailCmd;
import org.xujin.janus.app.command.co.ApiFilterDetailCO;
import org.xujin.janus.app.converter.ApiFilterClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.AlarmMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiFilterMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiFilterDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/15 11:10
 * @desc
 */
@Slf4j
@CmdHandler
public class ApiFilterDetailCmdExe implements CommandExecutorI<ResultData<ApiFilterDetailCO>, ApiFilterDetailCmd> {

    @Resource
    ApiFilterMapper apiFilterMapper;

    @Resource
    ApiFilterClientConverter converter;

    @Override
    public ResultData<ApiFilterDetailCO> execute(ApiFilterDetailCmd cmd) {
        ResultData<ApiFilterDetailCO> resultData = ResultData.success(null);
        check(cmd);
        ApiFilterDO detail = detail(cmd);
        ApiFilterDetailCO apiFilterDetailCO = converter.dataToClient(detail);
        resultData.setData(apiFilterDetailCO);
        return resultData;
    }

    protected boolean check(ApiFilterDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId()!=null,"id不能为空");
        return true;
    }

    public ApiFilterDO detail(ApiFilterDetailCmd cmd) {
        return apiFilterMapper.selectById(cmd.getId());
    }

}
