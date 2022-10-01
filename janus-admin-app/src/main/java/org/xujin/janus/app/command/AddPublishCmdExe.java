package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddPublishCmd;
import org.xujin.janus.app.command.cmo.AddPublishIpCmd;
import org.xujin.janus.app.converter.PublishClientConverter;
import org.xujin.janus.app.converter.PublishIpClientConverter;
import org.xujin.janus.domain.user.entity.PublishE;
import org.xujin.janus.domain.user.entity.PublishIpE;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/5/27 14:24
 * @desc
 */
@CmdHandler
public class AddPublishCmdExe implements CommandExecutorI<ResultData<Void>, AddPublishCmd> {

    @Resource
    private PublishClientConverter publishClientConverter;

    @Resource
    private PublishIpClientConverter publishIpClientConverter;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<Void> execute(AddPublishCmd cmd) {
        ResultData resultData = ResultData.success(null);
        check(cmd);
        //新增下发主表数据
        PublishE publishE = publishClientConverter.addCmdToEntity(cmd);
        publishE.setCreator(SessionUtils.getUserName());
        publishE.save();

        //新增灰度数据
        AddPublishIpCmd addPublishIpCmd = new AddPublishIpCmd();
        addPublishIpCmd.setPublishId(publishE.getId());
        addPublishIpCmd.setIps(cmd.getIps());
        PublishIpE publishIpE = publishIpClientConverter.addCmdToEntity(addPublishIpCmd);
        publishIpE.setCreator(SessionUtils.getUserName());
        publishIpE.save();
        return resultData;
    }
    protected boolean check(AddPublishCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkNotNull(cmd.getApplyId(), "申请id不能为null");
        Preconditions.checkNotNull(cmd.getIps(), "ip不能为null");
        return true;
    }
}
