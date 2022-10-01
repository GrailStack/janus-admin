package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceDraftDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/5 14:36
 **/
@Mapper
public interface OutServiceDraftMapper extends BaseMapper<OutServiceDraftDO> {

    @Delete("delete from t_out_service_draft where api_draft_id = #{apiDraftId}")
    int deleteByApiDraftId(@Param("apiDraftId") Long apiDraftId);

    @Select("select * from t_out_service_draft where api_draft_id = #{apiDraftId} order by id asc")
    List<OutServiceDraftDO> findByApiDraftId(@Param("apiDraftId") Long apiDraftId);

}
