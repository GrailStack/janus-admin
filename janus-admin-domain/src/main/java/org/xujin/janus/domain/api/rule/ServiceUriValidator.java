package org.xujin.janus.domain.api.rule;

import com.alibaba.fastjson.JSON;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.domain.api.constant.OutServiceTypeEnum;
import org.xujin.janus.domain.api.valueobject.ServiceAddress;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/8 15:23
 **/
@Component
public class ServiceUriValidator {

    public void validate(OutServiceTypeEnum type, String uri) {
        if (OutServiceTypeEnum.LOAD_BALANCE == type) {
            checkForLoadBalance(uri);
        } else if (OutServiceTypeEnum.STRAIGHT_FORWARD == type) {
            checkForStraightForward(uri);
        }
    }

    private void checkForLoadBalance(String uri) {
        List<ServiceAddress> addresses = null;
        try {
            addresses = JSON.parseArray(uri, ServiceAddress.class);
        } catch (Exception ex) {
            throw new BusinessException("400", "负载均衡服务的uri配置错误", ex);
        }
        Validate.notEmpty(addresses, "负载均衡服务的uri配置错误");
        addresses.forEach(address -> {
            checkServiceAddress(address);
        });
    }

    private void checkForStraightForward(String uri) {
        List<ServiceAddress> addresses = null;
        try {
            addresses = JSON.parseArray(uri, ServiceAddress.class);
        } catch (Exception ex) {
            throw new BusinessException("400", "直接转发服务的uri配置错误", ex);
        }
        Validate.notEmpty(addresses, "直接转发服务的uri配置错误");
        Validate.isTrue(addresses.size() == 1, "直接转发服务的uri配置错误");
        checkServiceAddress(addresses.get(0));
    }

    private void checkServiceAddress(ServiceAddress address) {
        Validate.notNull(address, "服务地址缺失");
        Validate.notBlank(address.getHost(), "服务地址缺失host");
        Validate.notNull(address.getPort(), "服务地址缺失port");
        Validate.inclusiveBetween(0, 0xFFFF, address.getPort(), "服务地址port超出范围");
    }

}
