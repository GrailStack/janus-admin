package org.xujin.janus.domain.user.service.impl;

import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.janus.domain.user.entity.MenuE;
import org.xujin.janus.domain.user.ports.MenuPort;
import org.xujin.janus.domain.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 14:42
 **/
@DomainService
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuPort menuPort;

    @Override
    public MenuE findMenuById(Long id) {
        return menuPort.findById(id);
    }

    @Override
    public List<MenuE> findMenusByParentId(Long parentId) {
        return menuPort.findMenusByParentId(parentId);
    }

    @Override
    public boolean deleteMenu(Long id) {
        Integer result = menuPort.deleteById(id);
        return result != null && result > 0;
    }

}
