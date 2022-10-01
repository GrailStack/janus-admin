package org.xujin.janus.app.extension;

import org.xujin.halo.annotation.extension.Extension;
import org.xujin.janus.app.command.co.JanusInstanceCO;
import org.xujin.janus.app.extension.extpt.JanusInstanceExtPt;

import java.util.List;

/**
 * 从eureka中获取Janus Instance
 * @author xujin
 */
@Extension(name="JanusInstanceFromEurekaExt",bizCode="janus.instance.source.eureka",desc = "从Eureka中获取实例")
public class JanusInstanceFromEurekaExt implements JanusInstanceExtPt {

    @Override
    public List<JanusInstanceCO> queryJanusInstanceByClusterCode(String clusterCode) {
        return null;
    }
}
