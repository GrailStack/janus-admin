package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.FilterE;

import java.math.BigInteger;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/4/22 14:31
 * @desc
 */
@Port
public interface FilterPort {
    /**
     * 新增filter
     * @param filterE
     */
    void addFilter(FilterE filterE);

    /**
     * 删除filter
     * @param filterE
     */
    void deleteFilter(FilterE filterE);

    /**
     * 更新filter
     * @param filterE
     */
    void updateFilter(FilterE filterE);

    /**
     * 查询filter
     * @param id
     * @return
     */
    FilterE findById(BigInteger id);

    /**
     * 根据id查询所有全局filter
     * @param ids
     * @return
     */
    List<String> findGlobalNames(List<Integer> ids);

}
