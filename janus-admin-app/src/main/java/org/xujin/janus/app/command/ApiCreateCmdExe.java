package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ApiCreateCmd;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.factory.ApiFactory;
import org.xujin.janus.domain.api.rule.ServiceUriValidator;
import org.xujin.janus.domain.api.service.ApiService;
import org.xujin.janus.domain.api.service.OutServiceConfigService;
import org.xujin.janus.infrastructure.utils.PropertyCopier;
import org.xujin.janus.infrastructure.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 11:38
 **/
@CmdHandler
public class ApiCreateCmdExe implements CommandExecutorI<ResultData<Long>, ApiCreateCmd> {

    @Autowired
    private ApiFactory apiFactory;

    @Autowired
    private ApiService apiService;

    @Autowired
    private OutServiceConfigService outServiceConfigService;

    @Autowired
    private ServiceUriValidator serviceUriValidator;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultData<Long> execute(ApiCreateCmd cmd) {
        check(cmd);
        ApiDraftE draftE = convert(cmd);
        draftE.save();
        outServiceConfigService.addConfigInDraft(cmd.getClusterCode(), draftE.getId(), cmd.getOutServiceConfig());
        return ResultData.success(draftE.getId());
    }

    private ApiDraftE convert(ApiCreateCmd cmd) {
        ApiDraftE draftE = apiFactory.createApiInDraftEForNewApi();
        draftE.setCreateUser(UserUtil.getCurrUser());
        draftE.setUpdateUser(draftE.getCreateUser());
        PropertyCopier.copyProperties(cmd, draftE);
        return draftE;
    }

    private void check(ApiCreateCmd cmd) {
        if (apiService.isNameExistInCluster(cmd.getClusterCode(), cmd.getName())) {
            throw new BusinessException("400", "Api名称已存在");
        }
        if (apiService.isApiExistInCluster(cmd.getClusterCode(), cmd.getPath(), cmd.getMethod().getCode())) {
            throw new BusinessException("400", "Api请求路径和方法已存在");
        }
        cmd.getOutServiceConfig().getOutServices().forEach(outService -> {
            serviceUriValidator.validate(outService.getType(), outService.getUri());
        });
    }

}
