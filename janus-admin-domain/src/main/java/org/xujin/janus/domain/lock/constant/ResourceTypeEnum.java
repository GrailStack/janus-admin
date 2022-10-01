package org.xujin.janus.domain.lock.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/1 14:42
 **/
public enum ResourceTypeEnum {

    CLUSTER("CLUSTER", "集群");

    ResourceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
