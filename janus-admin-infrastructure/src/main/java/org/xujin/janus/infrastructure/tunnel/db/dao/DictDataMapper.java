package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictDataDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapper<DictDataDO> {


    @Select("SELECT * FROM t_dict_data where is_deleted=0 and dict_code=#{dictCode}")
    List<DictDataDO> findDictDataByDictCode(@Param("dictCode") String dictCode);

    @Update("update t_dict_data set is_deleted=1  where dict_code=#{dictCode} ")
    void batchDeleteDictDataByDictCode(@Param("dictCode") String dictCode);

    @Select("SELECT * FROM t_dict_data where is_deleted=0 and status=#{status} and dict_code=#{dictCode}")
    List<DictDataDO> findDictDataByDictCodeAndStatus(@Param("dictCode") String dictCode, @Param("status") int status);


    @Select("SELECT * FROM t_dict_data where is_deleted=0 and dict_code=#{dictCode} and item_code=#{itemCode}")
    DictDataDO findDictDataByDictCodeAndItemCode(@Param("dictCode") String dictCode, @Param("itemCode") String itemCode);


    /**
     * 根据字典类型，是否启用和数据项code查询
     *
     * @param dictCode
     * @param status
     * @param itemCode
     * @return
     */
    @Select("SELECT * FROM t_dict_data where is_deleted=0 and status=#{status} and dict_code=#{dictCode} and item_code=#{itemCode}")
    DictDataDO findDictDataByDictCodeAndStatusAndItemCode(@Param("dictCode") String dictCode, @Param("status") int
            status, @Param("itemCode") String itemCode);

    default List<DictDataDO> findByDictCodes(List<String> dictCodes) {
        if (CollectionUtils.isEmpty(dictCodes)) {
            return Lists.newArrayList();
        }
        QueryWrapper<DictDataDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("dict_code", dictCodes);
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.eq("status", HaloConstant.ENABLE);
        queryWrapper.orderByAsc("id");
        return selectList(queryWrapper);
    }

}

