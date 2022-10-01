package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.MenuE;

import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/15 16:58
 * @desc
 */
@Port
public interface MenuPort {

    Long save(MenuE menuE);

    MenuE findById(Long id);

    List<MenuE> findMenusByParentId(Long parentId);

    Integer deleteById(Long id);
}
