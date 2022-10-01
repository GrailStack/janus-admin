package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AlarmDO;
import org.apache.ibatis.annotations.*;

/**
 * Desc:
 *
 * @author chenglong.ren
 * @date 2020/6/2 16:15
 **/
@Mapper
public interface AlarmMapper extends BaseMapper<AlarmDO> {

    @Select("select count(id)\n" +
            "from t_alarm\n" +
            "where status = 0\n" +
            "  and cluster_code =#{clusterCode}")
    Integer countByClusterCode(@Param("clusterCode")String clusterCode);
}
