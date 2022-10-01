package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 14:42
 **/
public enum ApiStatusEnum {

    ONLINE("ONLINE", "在线"),

    OFFLINE("OFFLINE", "已下线");

    ApiStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private String code;
    @Getter
    private String desc;

}
