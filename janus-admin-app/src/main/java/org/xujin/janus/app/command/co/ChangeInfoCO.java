package org.xujin.janus.app.command.co;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 17:49
 **/
@Data
public class ChangeInfoCO implements Serializable {

    private String sourceCode;

    private String sourceName;

    private Integer addCount;

    private Integer deleteCount;

    private Integer updateCount;

    private Integer totalCount;

    private List<ChangeItemCO> changeItems;

}
