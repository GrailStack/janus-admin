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
@TableName("t_dict_data")
@Data
public class DictDataDO {

    /**
     * 数据字典详细主键
     */
    @TableId(value = "id", type = AUTO)
    private Integer id;

    /**
     * 数据字典分类标识
     */
    @TableField(value = "dict_code")
    private String dictCode;

    /**
     * 数据字典详细名称
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 数据字典详细值
     */
    @TableField(value = "item_value")
    private String itemValue;

    /**
     * 数据字典详细描述
     */
    @TableField(value = "item_desc")
    private String itemDesc;


    /**
     * 数据项编码
     */
    @TableField(value = "item_code")
    private String itemCode;


    /**
     * 排序
     */
    @TableField(value = "item_sort")
    private Integer itemSort;

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

    /**
     * COMMENT '数据字典项启用状态，1：启用，0：未启用',
     */
    @TableField(value = "status")
    private Byte status;

}