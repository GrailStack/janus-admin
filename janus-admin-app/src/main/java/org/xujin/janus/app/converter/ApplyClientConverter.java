package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.AddApplyCmd;
import org.xujin.janus.app.command.cmo.DeleteApplyCmd;
import org.xujin.janus.app.command.cmo.UpdateApplyCmd;
import org.xujin.janus.app.command.co.ApplyDetailCO;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.factory.ApplyFactory;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApplyDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/5/26 15:56
 * @desc
 */
@Slf4j
@Component
public class ApplyClientConverter implements ConverterI {
    @Resource
    private ApplyFactory applyFactory;


    public ApplyE addCmdToEntity(AddApplyCmd cmd) {
        ApplyE applyE = applyFactory.createApplyE();
        BeanUtils.copyProperties(cmd,applyE);
        return applyE;
    }

    public ApplyE deleteCmdToEntity(DeleteApplyCmd cmd) {
        ApplyE applyE = applyFactory.createApplyE();
        applyE.setId(cmd.getId().longValue());
        applyE.setIsDeleted(HaloConstant.IS_DELETED_TRUE);
        return applyE;
    }
    public ApplyE updateCmdToEntity(UpdateApplyCmd cmd) {
        ApplyE applyE = applyFactory.createApplyE();
        BeanUtils.copyProperties(cmd,applyE);
        applyE.setId(cmd.getId().longValue());
        return applyE;
    }

    public ApplyDetailCO dataToClient(ApplyDO applyDO) {
        return BeanMapper.map(applyDO, ApplyDetailCO.class);
    }

    public List<ApplyDetailCO> dataToClients(List<ApplyDO> applyDOS) {
            return Optional.ofNullable(applyDOS).orElse(Lists.newArrayList())
                    .stream()
                    .map(this::dataToClient)
                    .collect(Collectors.toList());
    }

}
