package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:53
 * @desc
 */
@Mapper
public interface ApplyMapper extends BaseMapper<ApplyDO> {

    @Select("select count(id)\n" +
            "from t_apply\n" +
            "where status = 1\n" +
            "  and cluster_code =#{clusterCode}")
    Integer countByClusterCode(@Param("clusterCode") String clusterCode);


    @Update("update t_apply set status = #{status} where id = #{id} and is_deleted = 0")
    int updateStatus(@Param("id") Long id, @Param("status") String status);

}
