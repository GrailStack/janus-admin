package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.ChangeCollectCmd;
import org.xujin.janus.app.command.co.ChangeCollectionCO;
import org.xujin.janus.app.converter.ChangeClientConverter;
import org.xujin.janus.domain.change.ChangeManager;
import org.xujin.janus.domain.change.collect.ChangeInfo;
import org.xujin.janus.domain.change.collect.ChangeInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 18:17
 **/
@CmdHandler
public class ChangeCollectCmdExe implements CommandExecutorI<ResultData<ChangeCollectionCO>, ChangeCollectCmd> {

    @Autowired
    private ChangeManager changeManager;

    @Autowired
    private ChangeClientConverter changeClientConverter;

    @Override
    public ResultData<ChangeCollectionCO> execute(ChangeCollectCmd cmd) {
        ChangeInfoRequest request = new ChangeInfoRequest().setClusterCode(cmd.getClusterCode());
        List<ChangeInfo> changeInfos = changeManager.collectChanges(request);
        ChangeCollectionCO co = convertToCO(changeInfos);
        co.setClusterCode(cmd.getClusterCode());
        return ResultData.success(co);
    }

    private ChangeCollectionCO convertToCO(List<ChangeInfo> changeInfoList) {
        ChangeCollectionCO co = new ChangeCollectionCO();
        co.setChangeInfoList(changeClientConverter.convertChangeInfoList(changeInfoList));
        return co;
    }

}