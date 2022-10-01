package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import lombok.Data;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/9 16:28
 **/
@Data
public class ChangeSubmitCmd extends Command {

    private String clusterCode;

    private String description;

    private List<ChangeResource> submitChanges;

    @Data
    public static class ChangeResource {
        private String sourceCode;
        private List<Long> resourceIdList;
    }

}
