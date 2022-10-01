package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.ApiFilterE;


/**
 * @author chenglong.ren
 * @date 2020/6/17 11:52
 * @desc
 */
@Port
public interface ApiFilterPort {
    void save(ApiFilterE apiFliterE);
    void update(ApiFilterE apiFliterE);

}
