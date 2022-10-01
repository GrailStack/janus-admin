package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.UpdateDictTypeCmd;
import org.xujin.janus.domain.user.entity.DictTypeE;
import org.xujin.janus.domain.user.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;


@CmdHandler
public class UpdateDictTypeCmdExe implements CommandExecutorI<ResultData, UpdateDictTypeCmd> {

    @Autowired
    private MetaDataService metaDataService;

   @Override
   public ResultData execute(UpdateDictTypeCmd cmd) {
       DictTypeE dictTypeE=new DictTypeE();
       dictTypeE.setDictCode(cmd.getDictCode());
       dictTypeE.setDictName(cmd.getDictName());
       dictTypeE.setStatus(cmd.getStatus());
       dictTypeE.setId(cmd.getId());
       metaDataService.updateDictType(dictTypeE);
       return ResultData.success(null);

    }

}