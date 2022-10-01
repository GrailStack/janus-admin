package org.xujin.janus.app.command.co;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/27 11:26
 * @desc
 */
@Data
public class DynamicClassFileConfigCO implements Serializable {
    private List<String> directories;
    private Long pollingIntervalSeconds;
    private Long processFileThreads;
    private Long processFileThreadTimeOut;
}
