package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jin.xu
 * @date 2019/5/13
 */
@Data
public class UpdateAppCmd extends CommonCommand {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("应用ID")
    private String appId;


    @ApiModelProperty("域ID")
    private String domainId;


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

    @ApiModelProperty("接入Halo的版本")
    private String haloVersion;


}
