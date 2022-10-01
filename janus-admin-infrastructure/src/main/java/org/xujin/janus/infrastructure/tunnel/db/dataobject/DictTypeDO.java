package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author halo codegen
 */
@TableName("t_dict_type")
@Data
public class DictTypeDO {

    /**
     * 数据字典分类主键
     */
    @TableId(value = "id", type = AUTO)
    private Long id;

    /**
     * 数据字典分类名称
     */
    @TableField(value = "dict_name")
    private String dictName;

    /**
     * 数据字典分类唯一标识
     */
    @TableField(value = "dict_code")
    private String dictCode;

    /**
     * 数据字典分类启用状态，0：启用，1：未启用
     */
    @TableField(value = "status")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified")
    private Timestamp gmtModified;

    /**
     * 是否删除 0 否 1 已经删除
     */
    @TableField(value = "is_deleted")
    private Byte isDeleted;

}