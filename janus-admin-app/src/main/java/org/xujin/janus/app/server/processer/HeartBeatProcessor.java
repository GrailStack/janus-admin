package org.xujin.janus.app.server.processer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xujin.halo.annotation.app.AppService;
import org.xujin.janus.client.cmo.HeartBeatCmd;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.InstanceMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.InstanceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

@AppService
public class HeartBeatProcessor extends AbstractProcessor {
    @Autowired
    private InstanceMapper instanceMapper;

    @Override
    public JanusCmdMsg doExecute(JanusCmdMsg msg) {
        HeartBeatCmd heartBeatCmd = (HeartBeatCmd) msg.getPayload();
        String host = heartBeatCmd.getHost();
        String cluster = msg.getCluster();
        boolean hostExist = isHostExist(host, cluster);
        if (!hostExist) {
            InstanceDO instanceDO = new InstanceDO();
            instanceDO.setHost(host);
            instanceDO.setClusterCode(cluster);
            instanceDO.setStatus(new Byte("0"));
            instanceMapper.insert(instanceDO);
        }
        return successResponse(msg,heartBeatCmd);
    }

    public boolean isHostExist(String host, String cluster) {
        QueryWrapper<InstanceDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        queryWrapper.eq("host", host);
        queryWrapper.eq("cluster_code", cluster);
        List<InstanceDO> instanceDOS = instanceMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(instanceDOS)) {
            return true;
        }
        return false;
    }

}
