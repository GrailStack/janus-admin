package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import org.xujin.janus.infrastructure.tunnel.db.dataobject.AuditLogDO;

@Mapper
public interface AuditLogMapper extends BaseMapper<AuditLogDO> {


}
