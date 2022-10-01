package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ResourceLockDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 16:15
 **/
@Mapper
public interface ResourceLockMapper extends BaseMapper<ResourceLockDO> {

}
