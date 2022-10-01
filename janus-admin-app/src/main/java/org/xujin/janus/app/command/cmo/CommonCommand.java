package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author chenglong.ren
 * @date 2020/4/14 17:44
 * @desc
 */
@Data
public class CommonCommand extends Command {
    private String currentUserId;

    public CommonCommand() {
    }

    public void checkUserId() {
        if (StringUtils.isEmpty(currentUserId)) {
            throw new IllegalArgumentException("用户id不能为空");
        }
    }

}
