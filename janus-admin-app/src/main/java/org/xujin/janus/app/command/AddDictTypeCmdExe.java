package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.AddDictTypeCmd;
import org.xujin.janus.domain.user.entity.DictTypeE;
import org.xujin.janus.domain.user.service.MetaDataService;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.springframework.beans.factory.annotation.Autowired;


@CmdHandler
public class AddDictTypeCmdExe implements CommandExecutorI<ResultData, AddDictTypeCmd> {

    @Autowired
    private MetaDataService metaDataService;

   @Override
   public ResultData execute(AddDictTypeCmd cmd) {
       DictTypeE dictTypeE=new DictTypeE();
       dictTypeE.setDictCode(cmd.getDictCode());
       dictTypeE.setDictName(cmd.getDictName());
       dictTypeE.setStatus(cmd.getStatus());
       dictTypeE.setIsDeleted(HaloConstant.IS_DELETED_FALSE);
       metaDataService.addDictType(dictTypeE);
      return ResultData.success(null);

    }

}