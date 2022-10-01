package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ChangeReleaseCompleteCmd;
import org.xujin.janus.domain.change.ChangeManager;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.change.release.EndReleaseChangesRequest;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.factory.PublishFactory;
import org.xujin.janus.domain.user.service.ApplyService;
import org.xujin.janus.domain.user.service.PublishService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/18 18:05
 **/
@CmdHandler
public class ChangeReleaseCompleteCmdExe implements CommandExecutorI<ResultData<Boolean>, ChangeReleaseCompleteCmd> {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private PublishFactory publishFactory;

    @Autowired
    private PublishService publishService;

    @Autowired
    private ChangeManager changeManager;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultData<Boolean> execute(ChangeReleaseCompleteCmd cmd) {
        PublishE publishE = publishFactory.findPublishE(cmd.getPublishId());
        if (publishE == null) {
            throw new BusinessException("400", "发布记录不存在");
        }

        if (publishE.isPublishCompleted()) {
            throw new BusinessException("400", "已经发布完成");
        }

        if (publishE.isGrayPublish()) {
            checkForGray(publishE);
        } else {
            checkForFull(publishE);
        }

        publishE.updateStatusToCompleted();

        applyService.updateApplyStatus(publishE.getApplyId(), ApplyStatusEnum.RELEASED);

        EndReleaseChangesRequest request = new EndReleaseChangesRequest();
        request.setApplyId(publishE.getApplyId());
        changeManager.endReleaseChanges(request);

        return ResultData.success(true);
    }

    private void checkForGray(PublishE publishE) {
        List<PublishIpE> grayList = publishService.findPublishIpEList(publishE.getId(), ReleaseTypeEnum.GRAY);
        if (CollectionUtils.isNotEmpty(grayList) && !grayList.get(0).isGrayPublishCompleted()) {
            throw new BusinessException("400", "灰度下发中，无法完成");
        }
        List<PublishIpE> fullList = publishService.findPublishIpEList(publishE.getId(), ReleaseTypeEnum.FULL);
        checkIfCompletedForFull(fullList);
    }

    private void checkForFull(PublishE publishE) {
        List<PublishIpE> fullList = publishService.findPublishIpEList(publishE.getId(), ReleaseTypeEnum.FULL);
        checkIfCompletedForFull(fullList);
    }

    private void checkIfCompletedForFull(List<PublishIpE> fullList) {
        if (CollectionUtils.isEmpty(fullList)) {
            return;
        }
        for (PublishIpE publishIpE : fullList) {
            if (!publishIpE.isFullPublishCompleted()) {
                throw new BusinessException("400", "全量发布中，无法完成");
            }
        }
    }

}
