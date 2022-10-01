package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.ChangeOnlineStateCmd;
import org.xujin.janus.app.server.client.ServerRequests;
import org.xujin.janus.client.cmo.ChangeOnlineCmd;
import org.xujin.janus.domain.change.ChangeManager;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.submit.SubmitMultiChangeRequest;
import org.xujin.janus.domain.lock.ResourceLockService;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.xujin.janus.infrastructure.security.utils.UserUtil;
import org.xujin.janus.infrastructure.utils.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/9 16:37
 **/
@Slf4j
@CmdHandler
public class ChangeOnlineStateCmdExe implements CommandExecutorI<ResultData, ChangeOnlineStateCmd> {


    @Override
    public ResultData execute(ChangeOnlineStateCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        ChangeOnlineCmd changeOnlineCmd = new ChangeOnlineCmd();
        changeOnlineCmd.setOnline(cmd.isOnline());
        //拿到服务器ip:port
        String serverAddress = cmd.getIp() + ":" + cmd.getPort();
        try {
            ServerRequests.sendOnlineChanged(serverAddress, cmd.getClusterCode(), "0", changeOnlineCmd);
        } catch (Exception e) {
            throw new BusinessException("400", "改变状态失败:{}", e);
        }
        return resultData;
    }

    protected boolean check(ChangeOnlineStateCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getClusterCode(), "集群编码不能为null");
        Preconditions.checkNotNull(cmd.getIp(), "serverIp不能为null");
        Preconditions.checkNotNull(cmd.getPort(), "serverPort不能为null");
        return true;
    }


}

