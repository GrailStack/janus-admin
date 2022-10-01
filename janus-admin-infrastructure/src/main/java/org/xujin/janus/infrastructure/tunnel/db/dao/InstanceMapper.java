package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Desc:
 *
 * @author chenglong.ren
 * @date 2020/6/2 16:15
 **/
@Mapper
public interface InstanceMapper extends BaseMapper<InstanceDO> {

    @Select("select count(id)\n" +
            "from t_instance\n" +
            "where status = 0\n" +
            "  and cluster_code =#{clusterCode}")
    Integer countByClusterCode(@Param("clusterCode") String clusterCode);
}
