package org.xujin.janus.app.command.cmo;

import lombok.Data;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 18:11
 * @desc
 */
@Data
public class AddClusterUserCmd extends CommonCommand {
    private String clusterCode;
    private String userName;
    private String domainName;

    private List<String> addList;
}
