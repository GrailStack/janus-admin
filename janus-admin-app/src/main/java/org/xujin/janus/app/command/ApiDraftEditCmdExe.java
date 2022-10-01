package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApiDraftEditCmd;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.factory.ApiFactory;
import org.xujin.janus.domain.api.rule.ServiceUriValidator;
import org.xujin.janus.domain.api.service.OutServiceConfigService;
import org.xujin.janus.infrastructure.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 14:25
 **/
@CmdHandler
public class ApiDraftEditCmdExe implements CommandExecutorI<ResultData<Boolean>, ApiDraftEditCmd> {

    @Autowired
    private ApiFactory apiFactory;

    @Autowired
    private ServiceUriValidator serviceUriValidator;

    @Autowired
    private OutServiceConfigService outServiceConfigService;

    @Override
    @Transactional
    public ResultData<Boolean> execute(ApiDraftEditCmd cmd) {
        check(cmd);
        ApiDraftE draftE = apiFactory.createApiDraftE(cmd.getId());
        if (draftE == null) {
            throw new BusinessException("400", "记录不存在");
        }
        if (!draftE.canEdit()) {
            throw new BusinessException("500", "该条记录当前状态不支持编辑操作");
        }

        updateDraftE(draftE, cmd);
        draftE.save();

        outServiceConfigService.deleteConfigInDraft(draftE.getId());
        outServiceConfigService.addConfigInDraft(draftE.getClusterCode(), draftE.getId(), cmd.getOutServiceConfig());

        return ResultData.success(true);
    }

    private void check(ApiDraftEditCmd cmd) {
        cmd.getOutServiceConfig().getOutServices().forEach(outService -> {
            serviceUriValidator.validate(outService.getType(), outService.getUri());
        });
    }

    private void updateDraftE(ApiDraftE draftE, ApiDraftEditCmd cmd) {
        draftE.setUpdateUser(UserUtil.getCurrUser());
        draftE.setDescription(cmd.getDescription());
        draftE.setProtocol(cmd.getProtocol());
        draftE.setParamTransType(cmd.getParamTransType());
        draftE.setParamTransConfig(cmd.getParamTransConfig());
        draftE.setStatus(ApiDraftStatusEnum.EDITING);
    }

}
