package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PermissionDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author halo codegen
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionDO> {


    /**
     * 查询有效权限
     *
     * @param ids
     * @return
     */
    public default List<PermissionDO> findValidPermissions(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        QueryWrapper<PermissionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_deleted", 0);
        queryWrapper.in("id", ids);
        return Optional.ofNullable(selectList(queryWrapper)).orElse(Lists.newArrayList());
    }


    @Select("SELECT * FROM t_permission where is_deleted=0 and permission=#{permission} ")
    PermissionDO queryByPermission(@Param("permission")String permission);



    @Select("SELECT  p.`group` FROM t_permission p  where p.is_deleted=0  GROUP BY p.`group`")
    List<String> queryPerGroup();


    @Select("SELECT p.* FROM t_permission p  where p.is_deleted=0 and p.`group`= #{group}")
    List<PermissionDO> queryAllPermByGroup(@Param("group")String group);


    @Select("SELECT p.* FROM t_permission p,t_role_permission rp where p.is_deleted=0  and  p.id=rp.permission_id and " +
            "rp.role=#{role} and p.`group`=#{group} ")
    List<PermissionDO> queryPermissionByRoleAndGroup(@Param("group") String group,@Param("role") String role);




}