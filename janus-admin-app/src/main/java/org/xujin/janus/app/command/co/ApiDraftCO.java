package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.api.constant.ApiOperateTypeEnum;
import org.xujin.janus.domain.api.constant.ApiParamTransTypeEnum;
import org.xujin.janus.domain.api.constant.ApiProtocolEnum;
import org.xujin.janus.domain.api.valueobject.ApiParamTransConfig;
import org.xujin.janus.domain.api.valueobject.OutServiceConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/8 15:49
 **/
@Data
public class ApiDraftCO {

    private Long id;
    private Long apiId;
    private String name;
    private String description;
    private String clusterCode;
    protected String clusterName;
    private ApiProtocolEnum protocol;
    private String path;
    private String method;
    private ApiParamTransTypeEnum paramTransType;
    private ApiParamTransConfig paramTransConfig;
    private String createUser;
    protected String createUserName;
    private String updateUser;
    protected String updateUserName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Integer version;
    private ApiOperateTypeEnum operateType;
    private ApiDraftStatusEnum status;

    @ApiModelProperty("后端服务配置")
    private OutServiceConfig outServiceConfig;

    private Long applyId;

}
