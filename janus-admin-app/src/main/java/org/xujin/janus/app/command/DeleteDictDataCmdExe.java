package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.DeleteDictDataCmd;
import org.xujin.janus.domain.user.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;


@CmdHandler
public class DeleteDictDataCmdExe implements CommandExecutorI<ResultData, DeleteDictDataCmd> {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public ResultData execute(DeleteDictDataCmd cmd) {
        metaDataService.deleteDictData(cmd.getId());
        return ResultData.success(null);

    }

}