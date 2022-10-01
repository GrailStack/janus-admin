package org.xujin.janus.domain.change.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/15 13:44
 **/
public enum ReleaseStatusEnum {

    WAIT_PULL("WAIT_PULL", "待拉出"),

    WAIT_RELEASE("WAIT_RELEASE", "待下发"),

    RELEASING("RELEASING", "下发中"),

    PULLING("PULLING", "拉出中"),

    PULL_FAILED("PULL_FAILED", "拉出失败"),

    PULL_SUCCESS("PULL_SUCCESS", "拉出成功"),

    RELEASE_SUCCESS("RELEASE_SUCCESS", "下发成功"),

    RELEASE_FAIL("RELEASE_FAIL", "下发失败"),

    IGNORED("IGNORED", "忽略"),

    COMPLETED("COMPLETED", "完成");


    ReleaseStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private final String code;
    @Getter
    private final String desc;

}
