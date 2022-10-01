package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.Arrays;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/5 15:17
 **/
public enum ApiParamTransTypeEnum {

    TRANSPARENT("TRANSPARENT", "透传"),

    MAPPING_AND_FILTER("MAPPING_AND_FILTER", "入参映射（过滤未知参数）"),

    MAPPING_AND_TRANS("MAPPING_AND_TRANS", "入参映射（透传未知参数）");


    public static ApiParamTransTypeEnum getByCode(String code) {
        return Arrays.stream(ApiParamTransTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    ApiParamTransTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
