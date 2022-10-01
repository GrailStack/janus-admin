package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.Arrays;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/5 15:44
 **/
public enum ApiParamDataTypeEnum {

    INT("INT", "Int"),

    LONG("LONG", "Long"),

    FLOAT("FLOAT", "Float"),

    DOUBLE("DOUBLE", "Double"),

    STRING("STRING", "String"),

    BOOLEAN("BOOLEAN", "Boolean"),

    ARRAY("ARRAY", "Array");

    public static ApiParamDataTypeEnum getByCode(String code) {
        return Arrays.stream(ApiParamDataTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    ApiParamDataTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
