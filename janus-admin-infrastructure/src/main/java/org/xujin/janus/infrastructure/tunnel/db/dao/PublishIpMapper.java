package org.xujin.janus.infrastructure.tunnel.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishIpDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper
public interface PublishIpMapper extends BaseMapper<PublishIpDO> {

    @Select("select * from t_publish_ip where publish_id = #{publishId} and type = #{type} and is_deleted = 0")
    List<PublishIpDO> findByPublishIdAndType(@Param("publishId") Long publishId, @Param("type") String type);

    @Select("select * from t_publish_ip where id = #{id} and is_deleted = 0")
    PublishIpDO findById(@Param("id") Long id);

    @Update("update t_publish_ip set status = #{status} where id = #{id} and is_deleted = 0")
    int updateStatus(@Param("id") Long id, @Param("status") String status);

    default List<PublishIpDO> batchFindByPublishId(List<Long> publishIdList) {
        if (CollectionUtils.isEmpty(publishIdList)) {
            return Lists.newArrayList();
        }
        QueryWrapper<PublishIpDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("publish_id", publishIdList);
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByDesc("id");
        return selectList(queryWrapper);
    }

}
