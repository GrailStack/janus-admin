package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc: 后端服务类型
 *
 * @author yage.luan
 * @date 2020/6/6 9:44
 **/
public enum OutServiceTypeEnum {

    DISCOVERY("DISCOVERY", "服务发现"),

    LOAD_BALANCE("LOAD_BALANCE", "负载均衡"),

    STRAIGHT_FORWARD("STRAIGHT_FORWARD", "直接转发");

    OutServiceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private String code;
    @Getter
    private String desc;

}
