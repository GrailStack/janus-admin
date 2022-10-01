package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * @author yage.luan
 * created at 2019/5/11 18:15
 **/
@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {

    /**
     * @param parentId
     * @return
     */
    public default List<MenuDO> findMenusByParentId(Long parentId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        queryWrapper.eq("is_deleted", 0);
        return Optional.ofNullable(selectList(queryWrapper)).orElse(Lists.newArrayList());
    }

    /**
     * @return
     */
    public default List<MenuDO> getAllMenu() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return Optional.ofNullable(selectList(queryWrapper)).orElse(Lists.newArrayList());
    }

}
