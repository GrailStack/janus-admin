package org.xujin.janus.app.extension.extpt;

import org.xujin.halo.annotation.extension.ExtensionPoint;
import org.xujin.halo.extension.ExtensionPointI;
import org.xujin.janus.app.command.co.JanusInstanceCO;

import java.util.List;

@ExtensionPoint(name="获取网关实例扩展点",desc = "获取网关实例扩展点")
public interface JanusInstanceExtPt extends ExtensionPointI  {

    List<JanusInstanceCO> queryJanusInstanceByClusterCode(String clusterCode);

}
