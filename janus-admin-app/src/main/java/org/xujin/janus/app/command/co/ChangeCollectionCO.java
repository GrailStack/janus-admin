package org.xujin.janus.app.command.co;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 17:41
 **/
@Data
public class ChangeCollectionCO implements Serializable {

    private String clusterCode;

    private List<ChangeInfoCO> changeInfoList;

}
