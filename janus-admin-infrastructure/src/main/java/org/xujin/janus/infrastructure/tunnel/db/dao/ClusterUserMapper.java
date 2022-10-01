package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:34
 * @desc
 */
@Mapper
public interface ClusterUserMapper extends BaseMapper<ClusterUserDO> {

}
