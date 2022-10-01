package org.xujin.janus.domain.user.ports;

import org.xujin.janus.domain.user.entity.DictDataE;

/**
 * @author chenglong.ren
 * @date 2020/4/30 10:07
 * @desc
 */
public interface DictDataPort {
    void save(DictDataE dictDataE);

    void update(DictDataE dictDataE);

    void delete(DictDataE dictDataE);

    DictDataE findById(Long id);

    DictDataE findDictDataByDictCodeAndItemCode(String dictCode, String itemCode);
}
