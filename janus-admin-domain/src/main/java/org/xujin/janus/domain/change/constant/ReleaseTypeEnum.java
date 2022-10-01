package org.xujin.janus.domain.change.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/12 16:09
 **/
public enum ReleaseTypeEnum {

    GRAY("GRAY", "灰度"),

    FULL("FULL", "全量");

    ReleaseTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
