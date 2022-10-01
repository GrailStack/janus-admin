package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApiEditCmd;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.api.constant.ApiOperateTypeEnum;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.entity.ApiReleaseE;
import org.xujin.janus.domain.api.factory.ApiFactory;
import org.xujin.janus.domain.api.rule.ServiceUriValidator;
import org.xujin.janus.domain.api.service.OutServiceConfigService;
import org.xujin.janus.infrastructure.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 14:14
 **/
@Slf4j
@CmdHandler
public class ApiEditCmdExe implements CommandExecutorI<ResultData<Long>, ApiEditCmd> {

    @Autowired
    private ApiFactory apiFactory;

    @Autowired
    private ServiceUriValidator serviceUriValidator;

    @Autowired
    private OutServiceConfigService outServiceConfigService;

    @Override
    @Transactional
    public ResultData<Long> execute(ApiEditCmd cmd) {
        check(cmd);
        ApiReleaseE releaseE = apiFactory.createApiReleaseE(cmd.getId());
        if (releaseE == null) {
            throw new BusinessException("400", "Api不存在");
        }
        boolean locked = releaseE.lock(ApiOperateTypeEnum.UPDATE.getCode());
        if (!locked) {
            throw new BusinessException("400", "该Api在操作中，请稍后重试");
        }
        ApiDraftE draftE = convert(cmd, releaseE);
        draftE.save();
        outServiceConfigService.addConfigInDraft(draftE.getClusterCode(), draftE.getId(), cmd.getOutServiceConfig());
        return ResultData.success(draftE.getId());
    }

    private void check(ApiEditCmd cmd) {
        cmd.getOutServiceConfig().getOutServices().forEach(outService -> {
            serviceUriValidator.validate(outService.getType(), outService.getUri());
        });
    }

    private ApiDraftE convert(ApiEditCmd cmd, ApiReleaseE releaseE) {
        ApiDraftE draftE = apiFactory.createApiDraftE();

        draftE.setApiId(releaseE.getId());
        draftE.setName(releaseE.getName());
        draftE.setPath(releaseE.getPath());
        draftE.setMethod(releaseE.getMethod());
        draftE.setClusterCode(releaseE.getClusterCode());
        draftE.setVersion(releaseE.getVersion());

        draftE.setOperateType(ApiOperateTypeEnum.UPDATE);
        draftE.setStatus(ApiDraftStatusEnum.EDITING);
        draftE.setIsDeleted(false);
        draftE.setCreateUser(UserUtil.getCurrUser());
        draftE.setUpdateUser(draftE.getCreateUser());

        draftE.setDescription(cmd.getDescription());
        draftE.setProtocol(cmd.getProtocol());
        draftE.setParamTransType(cmd.getParamTransType());
        draftE.setParamTransConfig(cmd.getParamTransConfig());

        return draftE;
    }

}
