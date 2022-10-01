package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.event.EventBus;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ChangeFullPublishToServerCmd;
import org.xujin.janus.app.event.PublishToServerEvent;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.xujin.janus.domain.user.factory.PublishFactory;
import org.xujin.janus.domain.user.factory.PublishIpFactory;
import org.xujin.janus.domain.user.service.PublishService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/17 20:40
 **/
@CmdHandler
public class ChangeFullPublishToServerCmdExe implements CommandExecutorI<ResultData<Boolean>, ChangeFullPublishToServerCmd> {

    @Autowired
    private PublishIpFactory publishIpFactory;

    @Autowired
    private PublishFactory publishFactory;

    @Autowired
    private PublishService publishService;

    @Autowired
    private ApplyFactory applyFactory;

    @Autowired
    private EventBus eventBus;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultData<Boolean> execute(ChangeFullPublishToServerCmd cmd) {
        PublishE publishE = publishFactory.findPublishE(cmd.getPublishId());
        if (publishE == null) {
            throw new BusinessException("400", "待发布记录不存在");
        }
        List<PublishIpE> publishIpEList = publishService.findPublishIpEList(publishE.getId(), ReleaseTypeEnum.FULL);
        if (CollectionUtils.isEmpty(publishIpEList)) {
            throw new BusinessException("400", "无待全量发布记录");
        }
        boolean waitFullRelease = publishIpEList.stream().allMatch(PublishIpE::isWaitRelease);
        if (!waitFullRelease) {
            throw new BusinessException("400", "全量发布中，无需重复操作");
        }
        List<Long> publishIpIdList = publishIpEList.stream().map(PublishIpE::getId).collect(Collectors.toList());
        publishService.batchUpdatePublishIpStatus(publishIpIdList, ReleaseStatusEnum.RELEASING);

        List<String> ipList = publishIpEList.stream().map(PublishIpE::getIp).collect(Collectors.toList());

        ApplyE applyE = applyFactory.createApplyE(publishE.getApplyId());
        PublishToServerEvent event = new PublishToServerEvent();
        event.setApplyId(applyE.getId());
        event.setChanges(applyE.getChanges());
        event.setPublishId(publishE.getId());
        event.setIpList(ipList);
        eventBus.asyncPublishEvent(event);

        return ResultData.success(true);
    }

}
