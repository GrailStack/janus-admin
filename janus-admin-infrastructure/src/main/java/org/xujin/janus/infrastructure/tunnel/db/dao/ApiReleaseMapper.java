package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 16:15
 **/
@Mapper
public interface ApiReleaseMapper extends BaseMapper<ApiReleaseDO> {

    @Select("select * from t_api_release where id = #{id}")
    ApiReleaseDO findById(@Param("id") Long id);

    @Delete("delete from t_api_release where id = #{id} and status != 'ONLINE'")
    int deleteWithCheck(@Param("id") Long id);

    @Select("select * from t_api_release where cluster_code = #{clusterCode} and name = #{name} limit 1")
    ApiReleaseDO findByClusterCodeAndName(@Param("clusterCode") String clusterCode, @Param("name") String name);


    @Select("select * from t_api_release where cluster_code = #{clusterCode} ")
    List<ApiReleaseDO> findByClusterCode(@Param("clusterCode") String clusterCode);

}
