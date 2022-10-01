package org.xujin.janus.domain.change.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/18 10:14
 **/
public enum PublishOverActionEnum {

    IGNORED("IGNORED", "忽略"),

    COMPLETED("COMPLETED", "完成");

    PublishOverActionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
