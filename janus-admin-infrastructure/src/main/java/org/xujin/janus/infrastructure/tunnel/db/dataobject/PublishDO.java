package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.xujin.janus.domain.change.constant.ReleaseStatusEnum;
import org.xujin.janus.domain.change.constant.ReleaseTypeEnum;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author halo codegen
 */
@TableName("t_publish")
@Data
public class PublishDO {

    /**
     * PK
     */
    @TableId(value = "id", type = AUTO)
    private Long id;

    /**
     * 下发申请的Id
     */
    @TableField(value = "apply_id")
    private Long applyId;

    /**
     * 下发类型，0：灰度；1：全量
     */
    @TableField(value = "type")
    private ReleaseTypeEnum type;

    /**
     * 下发人
     */
    @TableField(value = "publisher")
    private String publisher;

    /**
     * 下发状态 0 下发中  1 忽略 2 下发完成
     */
    @TableField(value = "status")
    private ReleaseStatusEnum status;

    /**
     * 集群编码
     */
    @TableField(value = "cluster_code")
    private String clusterCode;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 修改者
     */
    @TableField(value = "modifier")
    private String modifier;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified")
    private Date gmtModified;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    @TableField(value = "is_deleted")
    private Byte isDeleted;

}