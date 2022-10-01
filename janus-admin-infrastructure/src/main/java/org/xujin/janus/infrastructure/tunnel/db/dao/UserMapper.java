package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * User数据对象
 * @author xujin
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    @Select("SELECT * FROM t_user u where u.is_deleted=0 and u.username=#{userName}")
    UserDO findUserByUserName(@Param("userName") String userName);

    @Select("SELECT * FROM t_user u where u.is_deleted=0 and u.username=#{userName} and u.password=#{password}")
    UserDO findUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);




}

