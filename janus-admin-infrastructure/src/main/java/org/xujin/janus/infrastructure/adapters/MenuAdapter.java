package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.user.entity.MenuE;
import org.xujin.janus.domain.user.ports.MenuPort;
import org.xujin.janus.infrastructure.converter.MenuConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.MenuMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.MenuDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/15 17:12
 * @desc
 */
@Adapter
public class MenuAdapter implements MenuPort {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuConverter menuConverter;

    @Override
    public Long save(MenuE menuE) {
        MenuDO menuDO = menuConverter.entityToData(menuE);
        if (menuDO.getId() == null) {
            menuMapper.insert(menuDO);
        } else {
            menuMapper.updateById(menuDO);
        }
        return menuDO.getId();
    }

    @Override
    public MenuE findById(Long id) {
        MenuDO menuDO = menuMapper.selectById(id);
        return menuConverter.dataToEntity(menuDO);
    }

    @Override
    public List<MenuE> findMenusByParentId(Long parentId) {
        List<MenuDO> menuDOList = menuMapper.findMenusByParentId(parentId);
        return menuConverter.dataToEntity(menuDOList);
    }

    @Override
    public Integer deleteById(Long id) {
        MenuDO menuDO = new MenuDO();
        menuDO.setId(id);
        menuDO.setIsDeleted((byte) 1);
        return menuMapper.updateById(menuDO);
    }
}
