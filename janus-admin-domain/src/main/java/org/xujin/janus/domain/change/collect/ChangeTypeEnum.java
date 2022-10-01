package org.xujin.janus.domain.change.collect;

import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 16:08
 **/
public enum ChangeTypeEnum {

    ADD("ADD", "新增"),

    UPDATE("UPDATE", "删除"),

    DELETE("DELETE", "修改");

    ChangeTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    private String code;
    @Getter
    private String desc;

}
