package org.xujin.janus.domain.user.repository;

import org.xujin.halo.annotation.domain.DomainRepository;
import org.xujin.halo.utils.reflect.BeanMapper;
import org.xujin.janus.domain.user.entity.DictTypeE;
import org.xujin.janus.domain.user.ports.DictTypePort;
import org.springframework.beans.factory.annotation.Autowired;

@DomainRepository
public class DictTypeRepository {

    @Autowired
    private DictTypePort dictTypePort;

    public void save(DictTypeE dictTypeE){
        dictTypePort.save(dictTypeE);
    }

    public void update(DictTypeE dictTypeE){
        dictTypePort.update(dictTypeE);
    }

    public DictTypeE findByDictCode(String dictCode){
        return dictTypePort.findByDictCode(dictCode);
    }

    public DictTypeE findById(Long id){
        return dictTypePort.findById(id);
    }

    public void delete(DictTypeE dictTypeE){
        dictTypePort.delete(dictTypeE);
    }

}