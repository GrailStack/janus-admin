package org.xujin.janus.app.server.processer.step;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.xujin.halo.annotation.app.Step;
import org.xujin.halo.command.BaseAppService;
import org.xujin.janus.client.co.api.DynamicClassConfig;
import org.xujin.janus.client.co.api.JanusInfluxConfig;
import org.xujin.janus.client.co.api.RoutesConfig;
import org.xujin.janus.client.co.api.ServerConfig;
import org.xujin.janus.client.co.api.netty.NettyClientConfig;
import org.xujin.janus.client.co.api.netty.NettyServerConfig;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.ClusterConfigMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterConfigDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 查询网关Server的启动参数
 *
 * @author xujin
 */
@Step
public class QueryStartUpStep implements BaseAppService<String, ServerConfig> {

    @Autowired
    private ClusterConfigMapper clusterConfigMapper;

    @Override
    public ServerConfig execute(String clusterCode) {
        String yml="";
        QueryWrapper<ClusterConfigDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", HaloConstant.IS_DELETED_FALSE);
        queryWrapper.eq("cluster_code", clusterCode);
        List<ClusterConfigDO> list = clusterConfigMapper.selectList(queryWrapper);
        for (int i = 0; i < list.size(); i++) {
            ClusterConfigDO x = list.get(i);
            String configValue = x.getConfigValue();
            yml=yml+configValue+"\r\n";;
        }
        ServerConfig serverConf=new Yaml().loadAs(yml,ServerConfig.class);
        return serverConf;
    }


}
