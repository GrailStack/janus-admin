package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PublishMapper extends BaseMapper<PublishDO> {

    @Select("select * from t_publish where apply_id = #{applyId} and is_deleted = 0 order by id desc limit 1")
    PublishDO findByApplyId(@Param("applyId") Long applyId);

    @Select("select * from t_publish where id = #{id} and is_deleted = 0 limit 1")
    PublishDO findById(@Param("id") Long id);

    @Update("update t_publish set status = #{status} where id = #{id} and is_deleted = 0")
    int updateStatus(@Param("id") Long id, @Param("status") String status);

}
