package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.query.QueryMetadataListCmd;
import org.xujin.janus.app.command.co.DictDataCO;
import org.xujin.janus.app.command.co.DictTypeListCO;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.DictDataMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.DictTypeMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictDataDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictTypeDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 分页数据字典对应的命令执行器
 * @author xujin
 */
@CmdHandler
public class QueryMetadataListCmdExe implements CommandExecutorI<ResultData<Map<String,List<DictTypeListCO>>>, QueryMetadataListCmd> {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

   @Override
   public ResultData<Map<String,List<DictTypeListCO>>> execute(QueryMetadataListCmd cmd) {
       Map<String, List<DictTypeListCO>> map = new HashMap<>();
       if (CollectionUtils.isNotEmpty(cmd.getSearchKeys())) {
           cmd.getSearchKeys().forEach(x->{
               QueryWrapper<DictTypeDO> queryWrapper = new QueryWrapper<>();
               if (StringUtils.isNotEmpty(x)) {
                   queryWrapper.and(qw -> qw
                           .like("dict_code", x).or()
                           .like("dict_name", x));
               }
               queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
               queryWrapper.orderByAsc("id");
               List<DictTypeDO> dictTypeDOS = dictTypeMapper.selectList(queryWrapper);
               List<DictTypeListCO> list= BeanMapper.mapList(dictTypeDOS,DictTypeDO.class,DictTypeListCO.class);
               for (DictTypeListCO dictTypeListCO:list) {
                   List<DictDataDO> dictDataList= dictDataMapper.findDictDataByDictCode(dictTypeListCO.getDictCode());
                   dictTypeListCO.setDictDataModelList(BeanMapper.mapList(dictDataList,DictDataDO.class, DictDataCO.class));
               }
               map.put(x, list);
           });
       } else {
           return ResultData.success(map);
       }
       return ResultData.success(map);

    }

}