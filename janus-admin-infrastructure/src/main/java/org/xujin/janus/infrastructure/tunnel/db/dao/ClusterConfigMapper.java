package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenglong.ren
 * @date 2020/5/27 9:53
 * @desc
 */
@Mapper
public interface ClusterConfigMapper extends BaseMapper<ClusterConfigDO> {

}
