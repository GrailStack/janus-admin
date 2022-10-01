package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.xujin.janus.domain.api.constant.ApiParamTransTypeEnum;
import org.xujin.janus.domain.api.constant.ApiProtocolEnum;
import org.xujin.janus.domain.api.constant.ApiStatusEnum;
import org.xujin.janus.domain.api.constant.HttpMethodEnum;
import org.xujin.janus.domain.api.valueobject.ApiParamTransConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.INPUT;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 14:46
 **/
@TableName("t_api_release")
@Data
public class ApiReleaseDO implements Serializable {

    @TableId(value = "id", type = INPUT)
    private Long id;

    private String name;
    private String description;
    private String clusterCode;
    private ApiProtocolEnum protocol;
    private String path;
    private HttpMethodEnum method;
    private ApiParamTransTypeEnum paramTransType;
    private ApiParamTransConfig paramTransConfig;
    private String createUser;
    private String updateUser;
    private Date createTime;
    private Date updateTime;
    private Integer version;
    private String lockType;
    private ApiStatusEnum status;

}
