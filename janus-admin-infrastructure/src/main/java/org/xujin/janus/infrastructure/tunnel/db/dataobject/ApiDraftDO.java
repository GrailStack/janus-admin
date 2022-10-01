package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.xujin.halo.mybatis.annotation.TableJSONField;
import org.xujin.janus.domain.api.constant.*;
import org.xujin.janus.domain.api.valueobject.ApiParamTransConfig;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 16:02
 **/
@TableName("t_api_draft")
@Data
public class ApiDraftDO implements Serializable {

    @TableId(value = "id", type = AUTO)
    private Long id;

    private Long apiId;
    private String name;
    private String description;
    private String clusterCode;
    private ApiProtocolEnum protocol;
    private String path;
    private HttpMethodEnum method;
    private ApiParamTransTypeEnum paramTransType;
    @TableJSONField
    private ApiParamTransConfig paramTransConfig;
    private String createUser;
    private String updateUser;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;
    private Integer version;
    private ApiOperateTypeEnum operateType;
    private ApiDraftStatusEnum status;
    private Long applyId;

}
