package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 9:28
 **/
public enum HttpMethodEnum {

    POST("POST", "POST"),
    GET("GET", "GET"),
    PUT("PUT", "PUT"),
    PATCH("PATCH", "PATCH"),
    DELETE("DELETE", "DELETE"),
    HEAD("HEAD", "HEAD"),
    OPTIONS("OPTIONS", "OPTIONS");


    HttpMethodEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private String code;
    @Getter
    private String desc;

}
