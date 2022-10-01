package org.xujin.janus.domain.user.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/10 16:11
 **/
public enum ApplyStatusEnum {

    WAIT_AUDIT("WAIT_AUDIT", "待审核"),

    AUDITED("AUDITED", "已审核"),

    REJECTED("REJECTED", "已驳回"),

    CANCELED("CANCELED", "已撤销"),

    RELEASING("RELEASING", "发布中"),

    RELEASED("RELEASED", "已发布");

    ApplyStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private String code;
    @Getter
    private String desc;

}
