package org.xujin.janus.app.command;

import org.xujin.janus.app.command.cmo.CommonCommand;
import lombok.Data;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/16 18:37
 **/
@Data
public class UpSertMenuCmd extends CommonCommand {

    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private List<String> roleList;

    private Integer sort;

    private String icon;

    private String menuKey;

}
