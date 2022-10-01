package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;
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
public interface OutServiceReleaseMapper extends BaseMapper<OutServiceReleaseDO> {

    @Delete("delete from t_out_service_release where api_release_id = #{apiReleaseId}")
    int deleteByApiReleaseId(Long apiReleaseId);

    @Select("select * from t_out_service_release where api_release_id = #{apiReleaseId} order by id asc")
    List<OutServiceReleaseDO> findByApiReleaseId(@Param("apiReleaseId") Long apiReleaseId);

}
