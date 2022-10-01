package org.xujin.janus.domain.user.ports;

import org.xujin.janus.domain.user.entity.DictTypeE;

/**
 * @author chenglong.ren
 * @date 2020/4/30 10:07
 * @desc
 */
public interface DictTypePort {
    void save(DictTypeE dictTypeE);

    void update(DictTypeE dictTypeE);

    DictTypeE findByDictCode(String dictCode);

    DictTypeE findById(Long id);

    void delete(DictTypeE dictTypeE);
}
