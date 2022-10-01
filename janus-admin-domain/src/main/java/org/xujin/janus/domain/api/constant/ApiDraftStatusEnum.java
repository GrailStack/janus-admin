package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 14:42
 **/
public enum ApiDraftStatusEnum {

    EDITING("EDITING", "编辑中"),

    WAIT_AUDIT("WAIT_AUDIT", "待审核"),

    AUDITED("AUDITED", "已审核"),

    REJECTED("REJECTED", "已驳回"),

    CANCELED("CANCELED", "已撤销"),

    RELEASING("RELEASING", "发布中"),

    RELEASED("RELEASED", "已发布");

    ApiDraftStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
