package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.Arrays;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/5 15:05
 **/
public enum ApiParamPositionEnum {

    PATH("PATH", "路径参数"),

    HEAD("HEAD", "头参数"),

    QUERY("QUERY", "查询参数"),

    FORM_BODY("FORM_BODY", "表单参数"),

    JSON_BODY("JSON_BODY", "Body参数");


    public static ApiParamPositionEnum getByCode(String code) {
        return Arrays.stream(ApiParamPositionEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    ApiParamPositionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
