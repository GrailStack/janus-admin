package org.xujin.janus.app.command.cmo;

import lombok.Data;

@Data
public class AddDictDataCmd extends CommonCommand {


    /**
     * 数据字典分类标识
     */
    private String dictCode;

    /**
     * 数据字典详细名称
     */
    private String itemName;

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
     * 数据项code
     */
    private String itemCode;


    /**
     * '数据字典项启用状态，1：启用，0：未启用',
     */
    private Byte status;



}


