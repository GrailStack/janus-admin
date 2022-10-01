package org.xujin.janus.app.command.cmo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:53
 * @desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("新增api-filter参数")
public class AddApiFilterCmd extends CommonCommand {
    private List<AddApiFilterDetailCmd> filters;
}
