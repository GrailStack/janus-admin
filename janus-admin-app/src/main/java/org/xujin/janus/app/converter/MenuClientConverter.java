package org.xujin.janus.app.converter;

import com.google.common.collect.Lists;
import org.xujin.halo.convertor.ConverterI;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.app.command.UpSertMenuCmd;
import org.xujin.janus.app.command.co.UserMenuCO;
import org.xujin.janus.domain.user.entity.MenuE;
import org.xujin.janus.domain.user.factory.MenuFactory;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.MenuDO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yage.luan
 * created at 2019/5/22 20:47
 **/
@Component
public class MenuClientConverter implements ConverterI {

    @Autowired
    private MenuFactory menuFactory;

    public MenuE cmdToEntity(UpSertMenuCmd cmd) {
        if (cmd == null) {
            return null;
        }
        MenuE menuE = BeanMapper.map(cmd, MenuE.class);
        menuE.setMenuRepository(menuFactory.getMenuRepository());
        return menuE;
    }

    public List<UserMenuCO> dataToClient(List<MenuDO> menuDOs) {
        return buildMenuTree(menuDOs);
    }

    protected List<UserMenuCO> buildMenuTree(List<MenuDO> menuDOs) {
        if (CollectionUtils.isEmpty(menuDOs)) {
            return Lists.newArrayList();
        }
        List<UserMenuCO> allMenus=new ArrayList<>();
        for (MenuDO menu:menuDOs) {
            UserMenuCO userMenuCO= BeanMapper.map(menu,UserMenuCO.class);
            userMenuCO.setRoleList(split(menu.getRoles()));
            allMenus.add(userMenuCO);
        }
        List<UserMenuCO> rootMenus = allMenus.stream().filter(menuCO -> {
            return menuCO.getParentId() == null || menuCO.getParentId().equals(Long.valueOf(0L));
        }).collect(Collectors.toList());

        Collections.sort(rootMenus, orderMenu());

        rootMenus.forEach(menuCO -> menuCO.setSubMenus(getChildMenus(menuCO.getId(), allMenus)));

        return rootMenus;
    }

    protected List<String> split(String str) {
        if (StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(str.split(","));
    }

    protected List<UserMenuCO> getChildMenus(Long menuId, List<UserMenuCO> allMenus) {
        List<UserMenuCO> childMenus = allMenus.stream()
                .filter(menuCO -> menuId.equals(menuCO.getParentId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(childMenus)) {
            return childMenus;
        }
        for (UserMenuCO menuCO : childMenus) {
            menuCO.setSubMenus(getChildMenus(menuCO.getId(), allMenus));
        }
        Collections.sort(childMenus, orderMenu());
        return childMenus;
    }

    protected Comparator<UserMenuCO> orderMenu() {
        Comparator<UserMenuCO> comparator = new Comparator<UserMenuCO>() {
            @Override
            public int compare(UserMenuCO o1, UserMenuCO o2) {
                if (o1.getSort() == null || o2.getSort() == null) {
                    return 0;
                }
                if (!o1.getSort().equals(o2.getSort())) {
                    return o2.getSort() - o1.getSort();
                }
                return 0;
            }
        };
        return comparator;
    }

}
