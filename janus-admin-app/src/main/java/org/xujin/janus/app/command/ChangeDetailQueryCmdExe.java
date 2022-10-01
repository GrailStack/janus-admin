package org.xujin.janus.app.command;

import com.alibaba.fastjson.JSON;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.ChangeDetailQueryCmd;
import org.xujin.janus.app.command.co.ChangeCollectionCO;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/12 16:48
 **/
@CmdHandler
public class ChangeDetailQueryCmdExe implements CommandExecutorI<ResultData<ChangeCollectionCO>, ChangeDetailQueryCmd> {

    @Autowired
    private ApplyFactory applyFactory;

    @Override
    public ResultData<ChangeCollectionCO> execute(ChangeDetailQueryCmd cmd) {
        ApplyE applyE = applyFactory.createApplyE(cmd.getApplyId());
        if (applyE == null) {
            throw new BusinessException("400", "审批记录不存在");
        }

        ChangeCollectionCO co = convertToCo(applyE.getChanges());
        if (co != null) {
            co.setClusterCode(applyE.getClusterCode());
        }

        return ResultData.success(co);
    }

    private ChangeCollectionCO convertToCo(String changes) {
        if (StringUtils.isBlank(changes)) {
            return null;
        }
        ChangeCollectionCO co = null;
        try {
            co = JSON.parseObject(changes, ChangeCollectionCO.class);
        } catch (Exception ex) {
            throw new BusinessException("500", "变更详情解析失败", ex);
        }
        return co;
    }

}
