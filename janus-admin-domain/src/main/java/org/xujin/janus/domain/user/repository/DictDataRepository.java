package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.DictDataE;
import org.xujin.janus.domain.user.ports.DictDataPort;
import org.springframework.beans.factory.annotation.Autowired;

@DomainRepository
public class DictDataRepository {

    @Autowired
    private DictDataPort dictDataPort;

    public void save(DictDataE dictDataE){
        dictDataPort.save(dictDataE);
    }

    public void update(DictDataE dictDataE){
        dictDataPort.update(dictDataE);
    }

    public void delete(DictDataE dictDataE) {
        dictDataPort.delete(dictDataE);
    }

    public DictDataE findById(Long id){
        return dictDataPort.findById(id);
    }

    public DictDataE findDictDataByDictCodeAndItemCode(String dictCode,String itemCode){
        return dictDataPort.findDictDataByDictCodeAndItemCode(dictCode, itemCode);
    }








}