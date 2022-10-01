package org.xujin.janus.app.command;

import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.event.EventBus;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ChangeReleaseCmd;
import org.xujin.janus.app.event.PublishToServerEvent;
import org.xujin.janus.domain.change.ChangeManager;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.change.release.StartReleaseChangesRequest;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.xujin.janus.domain.user.factory.PublishFactory;
import org.xujin.janus.domain.user.factory.PublishIpFactory;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/12 16:31
 **/
@CmdHandler
public class ChangeReleaseCmdExe implements CommandExecutorI<ResultData<Boolean>, ChangeReleaseCmd> {

    @Autowired
    private ApplyFactory applyFactory;

    @Autowired
    private PublishFactory publishFactory;

    @Autowired
    private PublishIpFactory publishIpFactory;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private EventBus eventBus;

    @Autowired
    private ChangeManager changeManager;

    @Override
    public ResultData<Boolean> execute(ChangeReleaseCmd cmd) {
        check(cmd);
        ApplyE applyE = applyFactory.createApplyE(cmd.getApplyId());
        if (applyE == null) {
            throw new BusinessException("400", "审批记录不存在");
        }
        if (!applyE.canRelease()) {
            throw new BusinessException("400", "记录当前状态不支持该操作");
        }

        Long publishId = exeInTx(applyE, cmd);

        PublishToServerEvent event = new PublishToServerEvent();
        event.setApplyId(applyE.getId());
        event.setChanges(applyE.getChanges());
        event.setPublishId(publishId);
        event.setIpList(cmd.getSelectedIpList());

        eventBus.asyncPublishEvent(event);

        return ResultData.success(true);
    }

    private void check(ChangeReleaseCmd cmd) {
        if (ReleaseTypeEnum.GRAY == cmd.getType()) {
            Validate.isTrue(cmd.getSelectedIpList().size() == 1, "灰度发布必须选择一个server");
        }
    }

    private Long exeInTx(ApplyE applyE, ChangeReleaseCmd cmd) {
        return transactionTemplate.execute(status -> {
            return publish(applyE, cmd);
        });
    }

    private Long publish(ApplyE applyE, ChangeReleaseCmd cmd) {
        applyE.updateStatusToReleasing();

        StartReleaseChangesRequest request = new StartReleaseChangesRequest();
        request.setApplyId(applyE.getId());
        changeManager.startReleaseChanges(request);

        PublishE publishE = publishFactory.createPublishEntity();
        publishE.setApplyId(applyE.getId());
        publishE.setClusterCode(applyE.getClusterCode());
        publishE.setStatus(ReleaseStatusEnum.RELEASING);
        publishE.setType(cmd.getType());
        publishE.setCreator(SessionUtils.getUserName());
        publishE.setModifier(SessionUtils.getUserName());
        publishE.setPublisher(SessionUtils.getUserName());
        publishE.save();

        if (ReleaseTypeEnum.GRAY == cmd.getType()) {
            saveToPublishIpForGray(publishE, cmd);
        } else {
            saveToPublishIpForFull(publishE, cmd);
        }
        return publishE.getId();
    }

    private void saveToPublishIpForGray(PublishE publishE, ChangeReleaseCmd cmd) {
        PublishIpE publishIpEForGray = publishIpFactory.createPublishIpEntity();
        publishIpEForGray.setPublishId(publishE.getId());
        publishIpEForGray.setStatus(ReleaseStatusEnum.WAIT_PULL);
        publishIpEForGray.setType(ReleaseTypeEnum.GRAY);
        publishIpEForGray.setIps(cmd.getSelectedIpList());
        publishIpEForGray.setCreator(SessionUtils.getUserName());
        publishIpEForGray.setModifier(SessionUtils.getUserName());
        publishIpEForGray.save();

        List<String> fullIpList = Lists.newArrayList(cmd.getIpList());
        fullIpList.removeAll(cmd.getSelectedIpList());
        if (!CollectionUtils.isEmpty(fullIpList)) {
            PublishIpE publishIpEForFull = publishIpFactory.createPublishIpEntity();
            publishIpEForFull.setPublishId(publishE.getId());
            publishIpEForFull.setStatus(ReleaseStatusEnum.WAIT_RELEASE);
            publishIpEForFull.setType(ReleaseTypeEnum.FULL);
            publishIpEForFull.setIps(fullIpList);
            publishIpEForFull.setCreator(SessionUtils.getUserName());
            publishIpEForFull.setModifier(SessionUtils.getUserName());
            publishIpEForFull.save();
        }
    }

    private void saveToPublishIpForFull(PublishE publishE, ChangeReleaseCmd cmd) {
        PublishIpE publishIpE = publishIpFactory.createPublishIpEntity();
        publishIpE.setPublishId(publishE.getId());
        publishIpE.setStatus(ReleaseStatusEnum.WAIT_RELEASE);
        publishIpE.setType(ReleaseTypeEnum.FULL);
        publishIpE.setIps(cmd.getIpList());
        publishIpE.setCreator(SessionUtils.getUserName());
        publishIpE.setModifier(SessionUtils.getUserName());
        publishIpE.save();
    }

}
