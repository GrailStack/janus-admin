package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.PageResult;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.query.PageQueryMetadataCmd;
import org.xujin.janus.app.command.co.DictDataCO;
import org.xujin.janus.app.command.co.DictTypeListCO;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.DictDataMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.DictTypeMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictDataDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictTypeDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 分页数据字典对应的命令执行器
 * @author xujin
 */
@CmdHandler
public class PageQueryMetadataCmdExe implements CommandExecutorI<ResultData<PageResult<DictTypeListCO>>, PageQueryMetadataCmd> {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

   @Override
   public ResultData<PageResult<DictTypeListCO>> execute(PageQueryMetadataCmd cmd) {
       Page pageRequest = new Page(cmd.getPageNo(),cmd.getPageSize());
       QueryWrapper<DictTypeDO> queryWrapper = new QueryWrapper<>();
       if (StringUtils.isNotEmpty(cmd.getSearchKey())) {
           queryWrapper.and(qw -> qw
                   .like("dict_code", cmd.getSearchKey()).or()
                   .like("dict_name", cmd.getSearchKey()));
       }
       queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
       queryWrapper.orderByDesc("id");
       IPage<DictTypeDO> page=dictTypeMapper.selectPage(pageRequest, queryWrapper);
       List<DictTypeListCO> list= BeanMapper.mapList(page.getRecords(),DictTypeDO.class,DictTypeListCO.class);
       for (DictTypeListCO dictTypeListCO:list) {
           List<DictDataDO> dictDataList= dictDataMapper.findDictDataByDictCode(dictTypeListCO.getDictCode());
           dictTypeListCO.setDictDataModelList(BeanMapper.mapList(dictDataList,DictDataDO.class, DictDataCO.class));
       }
       PageResult<DictTypeListCO> pageResult=new PageResult<DictTypeListCO>();
       pageResult.setCurrentPage(page.getCurrent());
       pageResult.setTotalCount(page.getTotal());
       pageResult.setList(list);
       pageResult.setTotalPage(page.getSize());
       return ResultData.success(pageResult);

    }

}