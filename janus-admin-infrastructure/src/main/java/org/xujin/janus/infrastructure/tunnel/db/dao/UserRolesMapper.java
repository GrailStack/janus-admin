package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserRolesDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yage.luan
 * created at 2019/5/11 18:12
 **/
@Mapper
public interface UserRolesMapper extends BaseMapper<UserRolesDO> {

    /**
     * 查找用户的角色列表
     *
     * @param userName
     * @return user roleList
     */
    public default List<String> findUserRoles(String userName) {
        QueryWrapper<UserRolesDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        queryWrapper.eq("is_deleted", 0);
        List<UserRolesDO> userRolesDOs = selectList(queryWrapper);
        return userRolesDOs.stream().map(UserRolesDO::getRole).collect(Collectors.toList());
    }

    @Select("select * from t_user_roles where is_deleted=0 and role=#{role} ")
    List<UserRolesDO> queryByRole(String role);

    @Select("select * from t_user_roles where is_deleted=0 and username=#{username} ")
    UserRolesDO queryByUserName(String username);


}
