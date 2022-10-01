package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictTypeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DictTypeMapper extends BaseMapper<DictTypeDO> {

    @Select("SELECT * FROM t_dict_type where is_deleted=0 and dict_code=#{dictCode}")
    DictTypeDO findByDictCode(@Param("dictCode") String dictCode);

    @Select("SELECT * FROM t_dict_type where is_deleted=0")
    List<DictTypeDO> findDictTypeList();


}
