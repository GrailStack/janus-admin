package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.xujin.janus.domain.user.constant.ApplyStatusEnum;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author halo codegen
 */
@TableName("t_apply")
@Data
public class ApplyDO {

    /**
     * 唯一id
     */
    @TableId(value = "id", type = AUTO)
    private Long id;

    /**
     * 集群编码
     */
    @TableField(value = "cluster_code")
    private String clusterCode;

    /**
     * 申请说明
     */
    @TableField(value = "description")
    private String description;

    /**
     * 请求入参
     */
    @TableField(value = "request")
    private String request;

    /**
     * 变更内容
     */
    @TableField(value = "changes")
    private String changes;

    /**
     * 申请人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 审批人
     */
    @TableField(value = "approver")
    private String approver;

    /**
     * 下发人
     */
    @TableField(value = "publisher")
    private String publisher;

    /**
     * 状态,1:审核中 2:审核通过 3:已下发  4:拒绝
     */
    @TableField(value = "status")
    private ApplyStatusEnum status;

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
     * 是否删除 0 否 1 已经删除
     */
    @TableField(value = "is_deleted")
    private Byte isDeleted;

}