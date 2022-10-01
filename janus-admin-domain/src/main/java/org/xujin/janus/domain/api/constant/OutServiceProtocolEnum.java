package org.xujin.janus.domain.api.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 10:38
 **/
public enum OutServiceProtocolEnum {

    HTTP("http","http协议"),
    HTTPS("https","https协议"),
    REST_LB_SC("lb://sc","Spring Cloud通过注册中心LB"),
    REST_LB_HOSTS("lb://hosts","通过Ip列表LB"),
    RPC_LB_DUBBO("lb://dubbo","dubbo通过注册中心LB"),
    RPC_LB_GRPC("lb://grpc","grpc通过注册中心LB");

    OutServiceProtocolEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    @EnumValue
    private String code;
    @Getter
    private String desc;

}
