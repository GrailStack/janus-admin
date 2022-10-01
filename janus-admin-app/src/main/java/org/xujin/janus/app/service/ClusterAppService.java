package org.xujin.janus.app.service;

import com.google.common.collect.Lists;
import org.xujin.halo.annotation.app.AppService;
import org.xujin.halo.context.Context;
import org.xujin.halo.extension.ExtensionExecutor;
import org.xujin.janus.app.command.co.JanusInstanceCO;
import org.xujin.janus.app.extension.extpt.JanusInstanceExtPt;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/16 18:11
 **/
@AppService
public class ClusterAppService {

    @Autowired
    private ExtensionExecutor extensionExecutor;

    public List<JanusInstanceCO> findClusterInstances(String clusterCode) {
        Context context = new Context();
        context.setBizCode("janus.instance.source.db");
        return extensionExecutor.exeReturnValue(JanusInstanceExtPt.class, context, extension -> extension.queryJanusInstanceByClusterCode(clusterCode));
    }

    public List<String> findClusterInstanceHosts(String clusterCode) {
        List<JanusInstanceCO> instances = findClusterInstances(clusterCode);
        if (CollectionUtils.isEmpty(instances)) {
            return Lists.newArrayList();
        }
        return instances.stream().map(JanusInstanceCO::getIp).collect(Collectors.toList());
    }

}
