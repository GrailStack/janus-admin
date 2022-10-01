package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.UpdateDictDataCmd;
import org.xujin.janus.domain.user.entity.DictDataE;
import org.xujin.janus.domain.user.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;


@CmdHandler
public class UpdateDictDataCmdExe implements CommandExecutorI<ResultData, UpdateDictDataCmd> {

    @Autowired
    private MetaDataService metaDataService;

   @Override
   public ResultData execute(UpdateDictDataCmd cmd) {
       DictDataE dictDataE= BeanMapper.map(cmd,DictDataE.class);
       metaDataService.updateDictData(dictDataE);
       return ResultData.success(null);

    }

}