package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.entity.ApiFilterE;
import org.xujin.janus.domain.user.ports.ApiFilterPort;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/6/17 11:51
 * @desc
 */
@DomainRepository
public class ApiFilterRepository {

    @Resource
    private ApiFilterPort port;

    public void save(ApiFilterE apiFliterE) {
        port.save(apiFliterE);
    }

    public void update(ApiFilterE apiFliterE) {
        port.update(apiFliterE);
    }
}
