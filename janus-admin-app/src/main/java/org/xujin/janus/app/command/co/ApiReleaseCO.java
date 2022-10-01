package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import org.xujin.janus.domain.api.constant.ApiParamTransTypeEnum;
import org.xujin.janus.domain.api.constant.ApiProtocolEnum;
import org.xujin.janus.domain.api.constant.ApiStatusEnum;
import org.xujin.janus.domain.api.constant.HttpMethodEnum;
import org.xujin.janus.domain.api.valueobject.ApiParamTransConfig;
import org.xujin.janus.domain.api.valueobject.OutServiceConfig;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/8 10:15
 **/
@Data
public class ApiReleaseCO {

    private Long id;

    private String name;

    private ApiProtocolEnum protocol;

    private String path;

    private HttpMethodEnum method;

    private String clusterCode;
    private String clusterName;

    private String createUser;
    private String createUserName;

    private String updateUser;
    private String updateUserName;

    private String lockType;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private ApiStatusEnum status;

    @ApiModelProperty("Api入参传递模式")
    private ApiParamTransTypeEnum paramTransType;

    @ApiModelProperty("Api入参传递配置")
    private ApiParamTransConfig paramTransConfig;

    @ApiModelProperty("后端服务配置")
    private OutServiceConfig outServiceConfig;

}
