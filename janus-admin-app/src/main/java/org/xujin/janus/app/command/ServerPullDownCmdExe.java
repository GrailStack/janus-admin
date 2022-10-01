package org.xujin.janus.app.command;

import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ServerPullDownCmd;
import org.xujin.janus.app.service.PublishAppService;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.factory.PublishFactory;
import org.xujin.janus.domain.user.factory.PublishIpFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/17 15:33
 **/
@CmdHandler
public class ServerPullDownCmdExe implements CommandExecutorI<ResultData<Boolean>, ServerPullDownCmd> {

    @Autowired
    private PublishFactory publishFactory;

    @Autowired
    private PublishIpFactory publishIpFactory;

    @Autowired
    private PublishAppService publishAppService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultData<Boolean> execute(ServerPullDownCmd cmd) {
        PublishIpE publishIpE = publishIpFactory.findPublishIpE(cmd.getPublishIpId());
        if (publishIpE == null) {
            throw new BusinessException("400", "待发布记录不存在");
        }
        if (ReleaseTypeEnum.FULL == publishIpE.getType()) {
            throw new BusinessException("400", "全量发布不支持拉出操作");
        }
        if (!canPullDownServer(publishIpE)) {
            throw new BusinessException("400", "当前状态不支持拉出操作");
        }
        publishIpE.updateStatusToPulling();

        PublishE publishE = publishFactory.findPublishE(publishIpE.getPublishId());

        publishAppService.changeServerOnlineStatus(publishE.getClusterCode(), publishIpE.getIp(), false, publishIpE.getId().toString());

        return ResultData.success(true);
    }

    private boolean canPullDownServer(PublishIpE publishIpE) {
        return Lists.newArrayList(
                ReleaseStatusEnum.WAIT_PULL, ReleaseStatusEnum.PULL_FAILED
        ).contains(publishIpE.getStatus());
    }

}
