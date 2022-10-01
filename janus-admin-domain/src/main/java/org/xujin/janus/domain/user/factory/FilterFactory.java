package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.FilterE;
import org.xujin.janus.domain.user.repository.FilterRepository;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:16
 * @desc
 */
@Factory
public class FilterFactory {

    @Resource
    private FilterRepository filterRepository;


    public FilterE createFilterE() {
        FilterE filterE = new FilterE();
        filterE.setRepository(filterRepository);
        return filterE;
    }

}
