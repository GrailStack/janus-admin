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
@TableName("t_publish_ip")
@Data
public class PublishIpDO {

    /**
     * PK
     */
    @TableId(value = "id", type = AUTO)
    private Long id;

    /**
     * publish PK
     */
    @TableField(value = "publish_id")
    private Long publishId;

    /**
     * 记录灰度下发IP
     */
    @TableField(value = "ip")
    private String ip;

    private ReleaseTypeEnum type;

    /**
     * 下发状态，成功或失败  1 成功，0 失败
     */
    @TableField(value = "status")
    private ReleaseStatusEnum status;

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