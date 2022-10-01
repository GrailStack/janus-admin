package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.DictDataE;
import org.xujin.janus.domain.user.ports.DictDataPort;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.DictDataMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictDataDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/4/30 10:09
 * @desc
 */
@Adapter
public class DictDataAdapter implements DictDataPort {

    @Autowired
    private DictDataMapper dictDataMapper;


    @Override
    public void save(DictDataE dictDataE) {
        dictDataMapper.insert(BeanMapper.map(dictDataE, DictDataDO.class));
    }

    @Override
    public void update(DictDataE dictDataE) {
        DictDataDO dictDataDO= dictDataMapper.selectById(dictDataE.getId());
        dictDataDO.setDictCode(dictDataE.getDictCode());
        dictDataDO.setItemDesc(dictDataE.getItemDesc());
        dictDataDO.setItemName(dictDataE.getItemName());
        dictDataDO.setItemSort(dictDataE.getItemSort());
        dictDataDO.setItemValue(dictDataE.getItemValue());
        dictDataDO.setItemCode(dictDataE.getItemCode());
        //更新status的状态
        dictDataDO.setStatus(dictDataE.getStatus());
        dictDataMapper.updateById(dictDataDO);
    }

    @Override
    public void delete(DictDataE dictDataE) {
        DictDataDO dictDataDO= dictDataMapper.selectById(dictDataE.getId());
        dictDataDO.setIsDeleted(HaloConstant.IS_DELETED_TRUE);
        dictDataMapper.updateById(dictDataDO);
    }

    @Override
    public DictDataE findById(Long id) {
        DictDataDO dictDataDO=dictDataMapper.selectById(id);
        return BeanMapper.map(dictDataDO,DictDataE.class);
    }

    @Override
    public DictDataE findDictDataByDictCodeAndItemCode(String dictCode, String itemCode) {
        DictDataDO dictDataDO= dictDataMapper.findDictDataByDictCodeAndItemCode(dictCode,itemCode);
        return BeanMapper.map(dictDataDO,DictDataE.class);
    }
}
