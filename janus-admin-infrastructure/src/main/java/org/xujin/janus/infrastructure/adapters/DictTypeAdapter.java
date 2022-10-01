package org.xujin.janus.infrastructure.adapters;

import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.DictTypeE;
import org.xujin.janus.domain.user.ports.DictTypePort;
import org.xujin.janus.infrastructure.common.HaloConstant;
import org.xujin.janus.infrastructure.tunnel.db.dao.DictDataMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.DictTypeMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.DictTypeDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/4/30 10:15
 * @desc
 */
@Adapter
public class DictTypeAdapter implements DictTypePort {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;


    @Override
    public void save(DictTypeE dictTypeE) {
        dictTypeMapper.insert(BeanMapper.map(dictTypeE, DictTypeDO.class));
    }

    @Override
    public void update(DictTypeE dictTypeE) {
        DictTypeDO dictTypeDO= dictTypeMapper.selectById(dictTypeE.getId());
        dictTypeDO.setDictCode(dictTypeE.getDictCode());
        dictTypeDO.setDictName(dictTypeE.getDictName());
        dictTypeDO.setStatus(dictTypeE.getStatus());
        dictTypeMapper.updateById(dictTypeDO);
    }

    @Override
    public DictTypeE findByDictCode(String dictCode) {
        DictTypeDO dictTypeDO=dictTypeMapper.findByDictCode(dictCode);
        return BeanMapper.map(dictTypeDO,DictTypeE.class);
    }

    @Override
    public DictTypeE findById(Long id) {
        DictTypeDO dictTypeDO=dictTypeMapper.selectById(id);
        return BeanMapper.map(dictTypeDO,DictTypeE.class);
    }

    @Override
    public void delete(DictTypeE dictTypeE) {
        DictTypeDO dictTypeDO= dictTypeMapper.selectById(dictTypeE.getId());
        dictTypeDO.setIsDeleted(HaloConstant.IS_DELETED_TRUE);
        dictTypeMapper.updateById(dictTypeDO);
        dictDataMapper.batchDeleteDictDataByDictCode(dictTypeE.getDictCode());
    }
}
