package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.janus.domain.user.repository.DictTypeRepository;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author xujin
 */
@Data
public class DictTypeE {

    /**
     * 数据字典分类主键
     */
    private Long id;

    /**
     * 数据字典分类名称
     */
    private String dictName;

    /**
     * 数据字典分类唯一标识
     */
    private String dictCode;

    /**
     * 数据字典分类启用状态，0：启用，1：未启用
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    private Timestamp gmtModified;

    /**
     * 是否删除 0 否 1 已经删除
     */
    private Byte isDeleted;

    private DictTypeRepository dictTypeRepository;

    @DomainAbility
    public void save(){
        dictTypeRepository.save(this);

    }


    @DomainAbility
    public void update(){
        dictTypeRepository.update(this);

    }

    @DomainAbility
    public void delete(){
        dictTypeRepository.delete(this);

    }


}