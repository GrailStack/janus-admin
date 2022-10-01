package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.AddClusterUserCmd;
import org.xujin.janus.app.command.cmo.DeleteClusterUserCmd;
import org.xujin.janus.app.command.cmo.UpdateClusterUserCmd;
import org.xujin.janus.infrastructure.ClusterUserDetailCo;
import org.xujin.janus.domain.user.entity.ClusterUserE;
import org.xujin.janus.domain.user.factory.ClusterUserFactory;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ClusterUserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/22 19:20
 * @desc
 */
@Slf4j
@Component
public class ClusterUserClientConverter implements ConverterI {

    @Resource
    private ClusterUserFactory factory;

    public ClusterUserE addCmdToEntity(AddClusterUserCmd clusterCmd) {
        ClusterUserE clusterUserE = factory.createClusterUserE();
        BeanUtils.copyProperties(clusterCmd, clusterUserE);
        return clusterUserE;
    }

    public ClusterUserE updateCmdToEntity(UpdateClusterUserCmd clusterCmd) {
        ClusterUserE clusterUserE = factory.createClusterUserE();
        BeanUtils.copyProperties(clusterCmd, clusterUserE);
        return clusterUserE;
    }

    public ClusterUserE deleteCmdToEntity(DeleteClusterUserCmd clusterCmd) {
        ClusterUserE clusterUserE = factory.createClusterUserE();
        BeanUtils.copyProperties(clusterCmd, clusterUserE);
        return clusterUserE;
    }

    public ClusterUserDetailCo dataToClient(ClusterUserDO clusterUserDO) {
        return BeanMapper.map(clusterUserDO, ClusterUserDetailCo.class);
    }

    public List<ClusterUserDetailCo> dataToClients(List<ClusterUserDO> clusterUserDOs) {
        return Optional.ofNullable(clusterUserDOs).orElse(Lists.newArrayList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }

}
