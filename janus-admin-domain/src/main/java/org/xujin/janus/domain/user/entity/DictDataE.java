package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.janus.domain.user.repository.DictDataRepository;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author xujin
 */
@Data
public class DictDataE {

    /**
     * 数据字典详细主键
     */
    private Long id;

    /**
     * 数据字典分类标识
     */
    private String dictCode;

    /**
     * 数据字典详细名称
     */
    private String itemName;


    private String itemCode;


    /**
     * 数据字典详细值
     */
    private String itemValue;

    /**
     * 数据字典详细描述
     */
    private String itemDesc;

    /**
     * 排序
     */
    private Integer itemSort;

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

    /**
     * '数据字典项启用状态，1：启用，0：未启用',
     */
    private Byte status;


    private DictDataRepository dictDataRepository;


    @DomainAbility
    public void save(){
        dictDataRepository.save(this);

    }

    @DomainAbility
    public void update(){
        dictDataRepository.update(this);

    }

    @DomainAbility
    public void delete(){
        dictDataRepository.delete(this);

    }






}