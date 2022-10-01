package org.xujin.janus.app.command.query;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryUserLoginDetailCmd;
import org.xujin.janus.app.command.co.UserLoginInfo;
import org.xujin.janus.domain.user.ports.ClusterUserPort;
import org.xujin.janus.infrastructure.ClusterUserDetailCo;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.converter.ClusterUserConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;

import static org.xujin.janus.infrastructure.common.HaloConstant.EMAIL;

/**
 * @author chenglong.ren
 * @date 2020/4/22 15:56
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryUserLoginDetailCmdExe implements CommandExecutorI<ResultData<UserLoginInfo>, QueryUserLoginDetailCmd> {


    @Override
    public ResultData<UserLoginInfo> execute(QueryUserLoginDetailCmd cmd) {
        ResultData<UserLoginInfo> resultData = ResultData.success(null);
        check(cmd);
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        String userName = cmd.getUserName();
        userLoginInfo.setName(userName);
        userLoginInfo.setEmail(userName + HaloConstant.EMAIL);
        userLoginInfo.setVersion(HaloConstant.VERSION);
        userLoginInfo.setCurrentTime(new Date());
        resultData.setData(userLoginInfo);
        return resultData;
    }

    protected boolean check(QueryUserLoginDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getUserName(), "userName不能为null");
        return true;
    }


}
