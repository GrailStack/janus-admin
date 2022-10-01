package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:36
 * @desc
 */
@Mapper
public interface FilterMapper extends BaseMapper<FilterDO> {
}
