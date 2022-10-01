package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddApiFilterCmd;
import org.xujin.janus.app.converter.ApiFilterClientConverter;
import org.xujin.janus.domain.user.entity.ApiFilterE;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/6/17 11:49
 * @desc
 */
@CmdHandler
public class AddApiFilterCmdExe implements CommandExecutorI<ResultData, AddApiFilterCmd> {

    @Resource
    private ApiFilterClientConverter converter;

    @Override
    public ResultData execute(AddApiFilterCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        List<ApiFilterE> apiFilterEs = converter.addCmdToEntity(cmd);
        apiFilterEs.forEach(ApiFilterE::save);
        return resultData;
    }

    protected boolean check(AddApiFilterCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getFilters(), "cmd不能为null");
        return true;
    }
}
