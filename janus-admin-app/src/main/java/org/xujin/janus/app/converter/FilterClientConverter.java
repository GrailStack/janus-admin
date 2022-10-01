package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.cmo.AddFilterCmd;
import org.xujin.janus.app.command.cmo.DeleteFilterCmd;
import org.xujin.janus.app.command.cmo.UpdateFilterCmd;
import org.xujin.janus.app.command.co.FilterDetailCo;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.factory.FilterFactory;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.FilterDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:15
 * @desc
 */
@Slf4j
@Component
public class FilterClientConverter implements ConverterI {

    @Resource
    private FilterFactory filterFactory;

    public Byte deleted = 1;

    public FilterE addCmdToEntity(AddFilterCmd cmd) {
        FilterE filterE = filterFactory.createFilterE();
        BeanUtils.copyProperties(cmd,filterE);
        return filterE;
    }

    public FilterE deleteCmdToEntity(DeleteFilterCmd cmd) {
        FilterE filterE = filterFactory.createFilterE();
        BeanUtils.copyProperties(cmd,filterE);
        return filterE;
    }

    public FilterE updateCmdToEntity(UpdateFilterCmd cmd) {
        FilterE filterE = filterFactory.createFilterE();
        BeanUtils.copyProperties(cmd,filterE);
        return filterE;
    }

    public FilterDetailCo dataToClient(FilterDO filterDO) {
        FilterDetailCo map = BeanMapper.map(filterDO, FilterDetailCo.class);
        if (null != map.getIsShare()) {
            map.setModelType(map.getIsShare().toString());
        }
        return map;
    }

    public List<FilterDetailCo> dataToClients(List<FilterDO> filterDOS) {
        return Optional.ofNullable(filterDOS).orElse(Lists.newArrayList())
                .stream()
                .map(this::dataToClient)
                .collect(Collectors.toList());
    }
}
