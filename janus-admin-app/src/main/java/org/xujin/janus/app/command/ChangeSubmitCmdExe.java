package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.ChangeSubmitCmd;
import org.xujin.janus.app.command.co.ChangeCollectionCO;
import org.xujin.janus.app.converter.ChangeClientConverter;
import org.xujin.janus.domain.change.ChangeManager;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.submit.SubmitMultiChangeRequest;
import org.xujin.janus.domain.lock.ResourceLockService;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.xujin.janus.infrastructure.security.utils.UserUtil;
import org.xujin.janus.infrastructure.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/9 16:37
 **/
@Slf4j
@CmdHandler
public class ChangeSubmitCmdExe implements CommandExecutorI<ResultData<Boolean>, ChangeSubmitCmd> {

    @Autowired
    private ChangeManager changeManager;

    @Autowired
    private ResourceLockService lockService;

    @Autowired
    private ApplyFactory applyFactory;

    @Autowired
    private ChangeClientConverter changeClientConverter;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultData<Boolean> execute(ChangeSubmitCmd cmd) {
        check(cmd);

        if (!lockService.lockCluster(cmd.getClusterCode())) {
            //todo 联调后替换 throw new BusinessException("500", "该集群有未完成操作，请稍后重试");
            log.warn("该集群({})有未完成操作，请稍后重试", cmd.getClusterCode());
        }

        SubmitMultiChangeRequest request = convert(cmd);

        ApplyE applyE = createApplyRecord(request);
        request.setApplyId(applyE.getId());

        changeManager.submitChanges(request);

        List<ChangeInfo> changeInfoList = changeManager.collectSubmitChanges(request);
        saveChangesToApply(applyE, changeInfoList);

        return ResultData.success(true);
    }

    private void saveChangesToApply(ApplyE applyE, List<ChangeInfo> changeInfoList) {
        ChangeCollectionCO co = new ChangeCollectionCO();
        co.setClusterCode(applyE.getClusterCode());
        co.setChangeInfoList(changeClientConverter.convertChangeInfoList(changeInfoList));
        applyE.setChanges(JSONUtil.toJSONStr(co));
        applyE.update();
    }

    private ApplyE createApplyRecord(SubmitMultiChangeRequest request) {
        ApplyE applyE = applyFactory.createApplyE();
        applyE.setCreator(UserUtil.getCurrUser());
        applyE.setDescription(request.getDescription());
        applyE.setClusterCode(request.getClusterCode());
        applyE.setRequest(JSONUtil.toJSONStr(request));
        applyE.setStatus(ApplyStatusEnum.WAIT_AUDIT);
        applyE.save();
        return applyE;
    }

    private SubmitMultiChangeRequest convert(ChangeSubmitCmd cmd) {
        return BeanMapper.map(cmd, SubmitMultiChangeRequest.class);
    }

    private void check(ChangeSubmitCmd cmd) {
        Validate.notEmpty(cmd.getSubmitChanges(), "submitChanges is required");
        for (ChangeSubmitCmd.ChangeResource request : cmd.getSubmitChanges()) {
            Validate.notNull(request, "submitChanges is required");
            Validate.notBlank(request.getSourceCode(), "sourceCode is required");
            Validate.notEmpty(request.getResourceIdList(), "resourceIdList is required");
            request.getResourceIdList().forEach(id -> {
                Validate.notNull(id, "resourceId is required");
            });
        }
    }

}

