package org.xujin.janus.app.command;

import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.event.EventBus;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ChangePublishToServerCmd;
import org.xujin.janus.app.event.PublishToServerEvent;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.xujin.janus.domain.user.factory.PublishFactory;
import org.xujin.janus.domain.user.factory.PublishIpFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/17 20:40
 **/
@CmdHandler
public class ChangePublishToServerCmdExe implements CommandExecutorI<ResultData<Boolean>, ChangePublishToServerCmd> {

    @Autowired
    private PublishIpFactory publishIpFactory;

    @Autowired
    private PublishFactory publishFactory;

    @Autowired
    private ApplyFactory applyFactory;

    @Autowired
    private EventBus eventBus;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultData<Boolean> execute(ChangePublishToServerCmd cmd) {
        PublishIpE publishIpE = publishIpFactory.findPublishIpE(cmd.getPublishIpId());
        if (publishIpE == null) {
            throw new BusinessException("400", "待发布记录不存在");
        }
        if (!publishIpE.canRelease()) {
            throw new BusinessException("400", "当前状态不支持该操作");
        }
        publishIpE.updateStatusToReleasing();

        PublishE publishE = publishFactory.findPublishE(publishIpE.getPublishId());
        ApplyE applyE = applyFactory.createApplyE(publishE.getApplyId());

        PublishToServerEvent event = new PublishToServerEvent();
        event.setApplyId(applyE.getId());
        event.setChanges(applyE.getChanges());
        event.setPublishId(publishE.getId());
        event.setIpList(Lists.newArrayList(publishIpE.getIp()));
        eventBus.asyncPublishEvent(event);

        return ResultData.success(true);
    }

}
