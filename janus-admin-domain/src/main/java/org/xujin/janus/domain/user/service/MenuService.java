package org.xujin.janus.domain.user.service;


import org.xujin.janus.domain.user.entity.MenuE;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 11:51
 **/
public interface MenuService {

    /**
     * @param id
     * @return
     */
    MenuE findMenuById(Long id);


    /**
     * @param parentId
     * @return
     */
    List<MenuE> findMenusByParentId(Long parentId);

    /**
     * @param id
     * @return
     */
    boolean deleteMenu(Long id);

}
