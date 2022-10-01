package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.DeleteDictTypeCmd;
import org.xujin.janus.domain.user.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;


@CmdHandler
public class DeleteDictTypeCmdExe implements CommandExecutorI<ResultData, DeleteDictTypeCmd> {

    @Autowired
    private MetaDataService metaDataService;

   @Override
   public ResultData execute(DeleteDictTypeCmd cmd) {
       metaDataService.deleteDictType(cmd.getId());
      return ResultData.success(null);

    }

}