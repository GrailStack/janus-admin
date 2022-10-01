package org.xujin.janus.infrastructure.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.MenuE;
import org.xujin.janus.domain.user.factory.MenuFactory;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.MenuDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 11:59
 **/
@Component
public class MenuConverter implements ConverterI {

    @Autowired
    private MenuFactory menuFactory;

    public MenuDO entityToData(MenuE menuE) {
        MenuDO menuDO = BeanMapper.map(menuE, MenuDO.class);
        menuDO.setRoles(join(menuE.getRoleList()));
        return menuDO;
    }

    protected String join(List<String> list) {
        return Optional.ofNullable(list).orElse(Lists.newArrayList())
                .stream()
                .reduce((a, b) -> a + "," + b)
                .orElse("");
    }

    public List<String> split(String str) {
        if (StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(str.split(","));
    }

    public MenuE dataToEntity(MenuDO menuDO) {
        if (menuDO == null) {
            return null;
        }
        MenuE menuE = BeanMapper.map(menuDO, MenuE.class);
        menuE.setRoleList(split(menuDO.getRoles()));
        return menuE;
    }

    public List<MenuE> dataToEntity(List<MenuDO> menuDOList) {
        List<MenuE> menuEList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(menuDOList)) {
            return menuEList;
        }
        for (MenuDO menuDO : menuDOList) {
            menuEList.add(dataToEntity(menuDO));
        }
        return menuEList;
    }

}
