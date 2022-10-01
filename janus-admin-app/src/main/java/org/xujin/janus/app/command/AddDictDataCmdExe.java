package org.xujin.janus.app.command;


import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.AddDictDataCmd;
import org.xujin.janus.domain.user.entity.DictDataE;
import org.xujin.janus.domain.user.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;


@CmdHandler
public class AddDictDataCmdExe implements CommandExecutorI<ResultData, AddDictDataCmd> {

    @Autowired
    private MetaDataService metaDataService;

    @Override
    public ResultData execute(AddDictDataCmd cmd) {
        DictDataE dictDataE= BeanMapper.map(cmd,DictDataE.class);
        metaDataService.addDictData(dictDataE);
      return ResultData.success(null);

    }

}