package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.ports.FilterPort;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:16
 * @desc
 */
@DomainRepository
public class FilterRepository {

    @Resource
    private FilterPort filterPort;

    public void save(FilterE filterE) {
        filterPort.addFilter(filterE);
    }

    public void delete(FilterE filterE) {
        filterPort.deleteFilter(filterE);
    }
}
