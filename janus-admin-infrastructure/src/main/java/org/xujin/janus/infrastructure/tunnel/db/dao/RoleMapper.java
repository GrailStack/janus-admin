package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author halo codegen
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

    /**
     * 查找有效的角色
     *
     * @param roles
     * @return
     */
    public default List<RoleDO> findValidRoles(List<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Lists.newArrayList();
        }
        QueryWrapper<RoleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("`key`", roles);
        queryWrapper.eq("is_deleted", 0);
        return Optional.ofNullable(selectList(queryWrapper)).orElse(Lists.newArrayList());
    }

    /**
     * @return
     */
    public default List<RoleDO> getAllRoles() {
        QueryWrapper<RoleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return Optional.ofNullable(selectList(queryWrapper)).orElse(Lists.newArrayList());
    }

    /**
     * 根据key查询角色
     * @param key
     * @return
     */
    @Select("SELECT * FROM t_role where is_deleted=0 and `key`=#{key} ")
    RoleDO queryRoleByKey(String key);

}