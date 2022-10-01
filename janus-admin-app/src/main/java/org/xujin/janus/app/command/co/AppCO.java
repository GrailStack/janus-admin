package org.xujin.janus.app.command.co;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author jin.xu
 * @date 2019/5/14
 */
@Data
@Accessors(chain = true)
public class AppCO {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("应用ID")
    private String appId;


    @ApiModelProperty("域ID")
    private String domainId;


    @ApiModelProperty("域ID")
    private String domainName;


    @ApiModelProperty("应用中文名")
    private String name;

    @ApiModelProperty("应用所有者")
    private String ownerName;

    @ApiModelProperty("应用所有者ID")
    private String ownerId;

    @ApiModelProperty("应用描述")
    private String description;

    @ApiModelProperty("spring名称")
    private String springApplicationName;

    @ApiModelProperty("应用git url")
    private String gitUrl;

    @ApiModelProperty("接入Halo状态")
    private Byte haloStatus;

    @ApiModelProperty("是否删除 0 否 1 已经删除")
    private Byte isDeleted;

    @ApiModelProperty("创建时间戳")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtCreate;

    @ApiModelProperty("修改时间戳")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtModified;

    @ApiModelProperty("halo框架版本")
    private String haloVersion;


}
