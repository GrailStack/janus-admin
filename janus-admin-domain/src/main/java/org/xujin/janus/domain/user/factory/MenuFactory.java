package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.MenuE;
import org.xujin.janus.domain.user.repository.MenuRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jin.xu
 * @date 2019/5/14
 */
@Factory
public class MenuFactory {

    @Autowired
    @Getter
    @Setter
    MenuRepository menuRepository;

    public MenuE create() {
        MenuE menuE = new MenuE();
        menuE.setMenuRepository(menuRepository);
        return menuE;
    }
}
