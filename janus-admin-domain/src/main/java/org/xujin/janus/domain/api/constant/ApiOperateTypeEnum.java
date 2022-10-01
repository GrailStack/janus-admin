package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 15:19
 **/
public enum ApiOperateTypeEnum {

    NEW("NEW", "新增"),

    UPDATE("UPDATE", "更新"),

    OFFLINE("OFFLINE", "下线"),

    ONLINE("ONLINE", "上线"),

    DELETE("DELETE", "删除");

    ApiOperateTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
