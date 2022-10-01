package org.xujin.janus.domain.user.service.impl;

import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.user.entity.DictDataE;
import org.xujin.janus.domain.user.entity.DictTypeE;
import org.xujin.janus.domain.user.repository.DictDataRepository;
import org.xujin.janus.domain.user.repository.DictTypeRepository;
import org.xujin.janus.domain.user.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;

@DomainService
public class MetaDataServiceImpl implements MetaDataService {

    @Autowired
    private DictTypeRepository dictTypeRepository;

    @Autowired
    private DictDataRepository dictDataRepository;


    @Override
    public void addDictType(DictTypeE dictTypeE) {
        DictTypeE dictTyp=dictTypeRepository.findByDictCode(dictTypeE.getDictCode());
        if(null!=dictTyp){
            throw new BusinessException("400","数据字典Code:"+dictTyp.getDictCode()+"不能重复");
        }
        dictTypeE.setDictTypeRepository(dictTypeRepository);
        dictTypeE.save();
    }

    @Override
    public void updateDictType(DictTypeE dictTypeE) {
        dictTypeE.setDictTypeRepository(dictTypeRepository);
        dictTypeE.update();
    }

    @Override
    public void deleteDictType(Long id) {
        DictTypeE dictType=dictTypeRepository.findById(id);
        if(null==dictType){
            throw new BusinessException("400","删除的数据不存在!");
        }
        dictType.setDictTypeRepository(dictTypeRepository);
        dictType.delete();

    }

    @Override
    public void addDictData(DictDataE dictDataE) {
        DictDataE dictData=dictDataRepository.findDictDataByDictCodeAndItemCode(dictDataE.getDictCode(),
                dictDataE.getItemCode());
        if(null!=dictData){
            throw new BusinessException("400","数据项Code:"+dictData.getItemCode()+"不能重复");
        }
        dictDataE.setDictDataRepository(dictDataRepository);
        dictDataE.save();
    }

    @Override
    public void updateDictData(DictDataE dictDataE) {
        dictDataE.setDictDataRepository(dictDataRepository);
        dictDataE.update();
    }

    @Override
    public void deleteDictData(Long id) {
        DictDataE dictDataE=dictDataRepository.findById(id);
        if(null==dictDataE){
            throw new BusinessException("400","删除的数据不存在!");
        }
        dictDataE.setDictDataRepository(dictDataRepository);
        dictDataE.delete();

    }
}
