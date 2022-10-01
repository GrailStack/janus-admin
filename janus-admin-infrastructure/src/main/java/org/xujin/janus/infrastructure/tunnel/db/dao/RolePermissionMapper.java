package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.RolePermissionDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author halo codegen
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionDO> {

    /**
     * 查询角色权限
     *
     * @param roles
     * @return
     */
    public default List<RolePermissionDO> findPermissionByRoles(Collection<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Lists.newArrayList();
        }
        QueryWrapper<RolePermissionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_deleted", 0);
        queryWrapper.in("role", roles);
        return Optional.ofNullable(selectList(queryWrapper)).orElse(Lists.newArrayList());
    }

    @Select("SELECT Count(*) FROM t_role_permission rp where rp.is_deleted=0  and  rp.permission_id=#{pid} and " +
            "rp.role=#{role} ")
    public int countRolePermByRoleAndPid(Long pid, String role);

    @Select("DELETE FROM t_role_permission  where permission_id=#{pid} and  role=#{role}")
    public void deleteByPidAndRole(Long pid, String role);

    @Select("DELETE FROM t_role_permission  where role=#{role}")
    public void deleteByRole(String role);
}