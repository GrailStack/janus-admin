package org.xujin.janus.domain.api.valueobject;

import com.alibaba.fastjson.annotation.JSONField;
import org.xujin.halo.annotation.domain.ValueObject;
import org.xujin.janus.domain.api.constant.*;
import lombok.Data;
import org.xujin.janus.domain.api.constant.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 20:45
 **/
@Data
@ValueObject
public class ApiDraftVO implements Serializable {

    private Long id;
    private Long apiId;
    private String name;
    private String description;
    private String clusterCode;
    private String clusterName;
    private ApiProtocolEnum protocol;
    private String path;
    private HttpMethodEnum method;
    private ApiParamTransTypeEnum paramTransType;
    private ApiParamTransConfig paramTransConfig;

    private String createUser;
    private String updateUser;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer version;
    private Boolean isDeleted;

    private ApiOperateTypeEnum operateType;
    private String operateTypeDesc;

    private ApiDraftStatusEnum status;
    private String statusDesc;

    private OutServiceConfig outServiceConfig;

}
