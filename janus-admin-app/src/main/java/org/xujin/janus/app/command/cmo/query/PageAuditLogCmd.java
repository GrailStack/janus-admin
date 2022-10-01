package org.xujin.janus.app.command.cmo.query;

import com.alibaba.fastjson.annotation.JSONField;
import org.xujin.janus.app.command.cmo.PageQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author chenglong.ren
 * @date 2020/4/15 13:47
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("审计日志查询参数")
public class PageAuditLogCmd extends PageQueryCommand {
    private String userName;

    private String type;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("开始时间")
    private String startTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("结束时间")
    private String endTime;

}
