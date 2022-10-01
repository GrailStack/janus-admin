package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterDO;

/**
 * @author chenglong.ren
 */
@Mapper
public interface ClusterMapper extends BaseMapper<ClusterDO> {
}
