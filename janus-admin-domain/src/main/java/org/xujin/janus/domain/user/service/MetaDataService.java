package org.xujin.janus.domain.user.service;


import org.xujin.janus.domain.user.entity.DictDataE;
import org.xujin.janus.domain.user.entity.DictTypeE;

public interface MetaDataService {

    void addDictType(DictTypeE dictTypeE);

    void updateDictType(DictTypeE dictTypeE);

    /**
     * 通过id删除数据字典类型
     * @param id
     */
    void deleteDictType(Long id);


    /**
     * 增加数据字典项
     * @param dictDataE
     */
    void addDictData(DictDataE dictDataE);

    /**
     * 修改数据字典项
     * @param dictDataE
     */
    void updateDictData(DictDataE dictDataE);

    /**
     * 通过id删除数据字典项
     * @param id
     */
    void deleteDictData(Long id);


}
