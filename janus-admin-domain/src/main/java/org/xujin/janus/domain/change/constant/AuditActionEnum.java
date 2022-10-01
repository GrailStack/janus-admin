package org.xujin.janus.domain.change.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/11 13:25
 **/
public enum AuditActionEnum {

    AUDITED("AUDITED", "通过"),

    REJECTED("REJECTED", "驳回"),

    CANCELED("CANCELED", "撤销");

    AuditActionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
