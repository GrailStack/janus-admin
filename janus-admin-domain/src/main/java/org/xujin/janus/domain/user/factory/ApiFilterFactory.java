package org.xujin.janus.domain.user.factory;

import org.xujin.halo.annotation.domain.Factory;
import org.xujin.janus.domain.user.entity.ApiFilterE;
import org.xujin.janus.domain.user.repository.ApiFilterRepository;

import javax.annotation.Resource;

/**
 * @author chenglong.ren
 * @date 2020/6/17 11:51
 * @desc
 */
@Factory
public class ApiFilterFactory {
    @Resource
    private ApiFilterRepository apiFilterRepository;

    public ApiFilterE createApiFilterE() {
        ApiFilterE apiFilterE = new ApiFilterE();
        apiFilterE.setApiFilterRepository(apiFilterRepository);
        return apiFilterE;
    }

}
