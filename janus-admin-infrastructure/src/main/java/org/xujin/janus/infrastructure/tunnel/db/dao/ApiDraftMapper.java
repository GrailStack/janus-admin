package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiDraftDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 16:15
 **/
@Mapper
public interface ApiDraftMapper extends BaseMapper<ApiDraftDO> {

    @Update("update t_api_draft set api_id = #{id} where id = #{id} and is_deleted = 0")
    int updateApiIdToId(@Param("id") Long id);

    @Select("select * from t_api_draft where id = #{id} and is_deleted = 0")
    ApiDraftDO findUnDeletedById(@Param("id") Long id);

    @Update("update t_api_draft set status = #{status} where id = #{id} and is_deleted = 0")
    int updateStatusById(@Param("id") Long id, @Param("status") String status);

}
