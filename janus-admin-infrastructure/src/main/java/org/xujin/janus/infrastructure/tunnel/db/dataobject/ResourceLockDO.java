package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import org.xujin.janus.domain.lock.constant.ResourceTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/10 11:25
 **/
@TableName("t_resource_lock")
@Data
public class ResourceLockDO {

    private Long id;
    private ResourceTypeEnum resourceType;
    private String resourceId;
    private String operation;
    private Date createTime;
    private Date updateTime;

}
