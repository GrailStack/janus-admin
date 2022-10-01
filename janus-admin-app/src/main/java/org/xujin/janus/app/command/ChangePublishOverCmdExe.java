package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ChangePublishOverCmd;
import org.xujin.janus.domain.change.constant.PublishOverActionEnum;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.domain.user.factory.PublishIpFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/18 10:18
 **/
@CmdHandler
public class ChangePublishOverCmdExe implements CommandExecutorI<ResultData<Boolean>, ChangePublishOverCmd> {

    @Autowired
    private PublishIpFactory publishIpFactory;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultData<Boolean> execute(ChangePublishOverCmd cmd) {
        PublishIpE publishIpE = publishIpFactory.findPublishIpE(cmd.getPublishIpId());
        if (publishIpE == null) {
            throw new BusinessException("400", "该记录不存在");
        }

        if (PublishOverActionEnum.COMPLETED == cmd.getAction()) {
            onCompleted(publishIpE);
        } else {
            onIgnored(publishIpE);
        }

        return ResultData.success(true);
    }

    private void onCompleted(PublishIpE publishIpE) {
        if (ReleaseStatusEnum.RELEASE_SUCCESS != publishIpE.getStatus()) {
            throw new BusinessException("400", "未发布成功，无法完成");
        }
        publishIpE.updateStatusToCompleted();
    }

    private void onIgnored(PublishIpE publishIpE) {
        if (ReleaseTypeEnum.GRAY == publishIpE.getType()) {
            throw new BusinessException("400", "灰度发布，无法忽略");
        }
        publishIpE.updateStatusToIgnored();
    }

}
