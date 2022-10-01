package org.xujin.janus.app.converter;

import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.*;
import org.xujin.janus.app.command.cmo.AddApiFilterDetailCmd;
import org.xujin.janus.app.command.co.ApiFilterDetailCO;
import org.xujin.janus.domain.user.entity.ApiFilterE;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.factory.ApiFilterFactory;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiFilterDO;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.xujin.janus.app.command.cmo.AddApiFilterCmd;
import org.xujin.janus.app.command.cmo.UpdateApiFilterCmd;
import org.xujin.janus.app.command.cmo.UpdateApiFilterDetailCmd;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/6/17 11:50
 * @desc
 */
@Slf4j
@Component
public class ApiFilterClientConverter  implements ConverterI {

    @Resource
    private ApiFilterFactory apiFilterFactory;


    public List<ApiFilterE> addCmdToEntity(AddApiFilterCmd cmd) {
        List<ApiFilterE> list = new ArrayList<>();
        List<AddApiFilterDetailCmd> filters = cmd.getFilters();
        filters.forEach(x->{
            ApiFilterE apiFilterE = apiFilterFactory.createApiFilterE();
            BeanUtils.copyProperties(x,apiFilterE);
            apiFilterE.setCreator(SessionUtils.getUserName());
            list.add(apiFilterE);
        });
        return list;
    }

    public List<ApiFilterE> updateCmdToEntity(UpdateApiFilterCmd cmd) {
        List<ApiFilterE> list = new ArrayList<>();
        List<UpdateApiFilterDetailCmd> filters = cmd.getFilters();
        filters.forEach(x->{
            ApiFilterE apiFilterE = apiFilterFactory.createApiFilterE();
            BeanUtils.copyProperties(x,apiFilterE);
            apiFilterE.setModifier(SessionUtils.getUserName());
            list.add(apiFilterE);
        });
        return list;
    }

    public ApiFilterDetailCO dataToClient(ApiFilterDO apiFilterDO) {
        return BeanMapper.map(apiFilterDO, ApiFilterDetailCO.class);
    }

    public List<ApiFilterDetailCO> dataToClients(List<ApiFilterDO> apiFilterDOS) {
        return Optional.ofNullable(apiFilterDOS).orElse(Arrays.asList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }
}
