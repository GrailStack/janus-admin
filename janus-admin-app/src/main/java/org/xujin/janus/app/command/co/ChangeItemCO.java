package org.xujin.janus.app.command.co;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 17:57
 **/
@Data
public class ChangeItemCO implements Serializable {

    private String changeType;

    private String changeTypeDesc;

    private Long resourceId;

    private Object newData;

    private Object oldData;

}
