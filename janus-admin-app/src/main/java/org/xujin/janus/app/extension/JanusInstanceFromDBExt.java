package org.xujin.janus.app.extension;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.extension.Extension;
import org.xujin.janus.app.command.co.JanusInstanceCO;
import org.xujin.janus.app.extension.extpt.JanusInstanceExtPt;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.InstanceMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 从DB中获取Janus Instance
 * @author xujin
 */
@Extension(name="JanusInstanceFromDBExt",bizCode="janus.instance.source.db",desc = "从Eureka中获取实例")
public class JanusInstanceFromDBExt implements JanusInstanceExtPt {

    @Resource
    private InstanceMapper instanceMapper;

    @Override
    public List<JanusInstanceCO> queryJanusInstanceByClusterCode(String clusterCode) {
        List<JanusInstanceCO> result = new ArrayList<>();
        QueryWrapper<InstanceDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        queryWrapper.eq("cluster_code", clusterCode);
        List<InstanceDO> instanceDOS = instanceMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(instanceDOS)) {
            instanceDOS.forEach(x->{
                JanusInstanceCO janusInstanceCO = new JanusInstanceCO();
                janusInstanceCO.setIp(x.getHost());
                janusInstanceCO.setClusterCode(x.getClusterCode());
                janusInstanceCO.setInstanceId(x.getId().toString());
                result.add(janusInstanceCO);
            });
        }
        return result;
    }
}
