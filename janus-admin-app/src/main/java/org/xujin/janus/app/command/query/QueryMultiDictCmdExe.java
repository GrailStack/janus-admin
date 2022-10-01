package org.xujin.janus.app.command.query;

import com.google.common.collect.Lists;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.query.QueryMultiDictCmd;
import org.xujin.janus.app.command.co.DictDataCO;
import org.xujin.janus.infrastructure.tunnel.db.dao.DictDataMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictDataDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/19 20:54
 **/
@CmdHandler
public class QueryMultiDictCmdExe implements CommandExecutorI<ResultData<Map<String, List<DictDataCO>>>, QueryMultiDictCmd> {

    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public ResultData<Map<String, List<DictDataCO>>> execute(QueryMultiDictCmd cmd) {
        List<DictDataDO> doList = dictDataMapper.findByDictCodes(cmd.getDictCodes());
        List<DictDataCO> coList = convert(doList);
        Map<String, List<DictDataCO>> dictCode2Co = coList.stream().collect(Collectors.groupingBy(DictDataCO::getDictCode));
        cmd.getDictCodes().forEach(code -> {
            dictCode2Co.putIfAbsent(code, Lists.newArrayList());
        });
        return ResultData.success(dictCode2Co);
    }

    private List<DictDataCO> convert(List<DictDataDO> doList) {
        if (CollectionUtils.isEmpty(doList)) {
            return Lists.newArrayList();
        }
        return BeanMapper.mapList(doList, DictDataDO.class, DictDataCO.class);
    }

}
