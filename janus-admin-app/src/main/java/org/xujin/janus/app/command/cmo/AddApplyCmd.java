package org.xujin.janus.app.command.cmo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:53
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("新增审批参数")
public class AddApplyCmd  extends CommonCommand {

    private String clusterCode;

    private String description;


    private String changes;


    private String creator;


    private String approver;


    private String publisher;



}
