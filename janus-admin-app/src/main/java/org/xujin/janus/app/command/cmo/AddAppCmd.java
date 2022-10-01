package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jin.xu
 * @date 2019/5/13
 */
@Data
public class AddAppCmd extends CommonCommand {

    @ApiModelProperty("应用ID")
    private String appId;


    @ApiModelProperty("应用ID")
    private String  domainId;

    @ApiModelProperty("应用中文名")
    private String name;

    @ApiModelProperty("应用所有者")
    private String ownerName;

    @ApiModelProperty("应用所有者ID")
    private String ownerId;

    @ApiModelProperty("应用描述")
    private String description;

    @ApiModelProperty("spring应用名称")
    private String springApplicationName;

    @ApiModelProperty("应用git url")
    private String gitUrl;

    @ApiModelProperty("应用是否接入halo 0：未接入，1：接入")
    private Byte haloStatus;

    @ApiModelProperty("应用所用Halo框架的版本")
    private String haloVersion;

}
