package org.xujin.janus.infrastructure.utils;

import org.xujin.halo.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/3 17:34
 **/
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyCopier {

    public static void copyProperties(final Object source, final Object target) throws BusinessException {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
        } catch (Exception ex) {
            String msg = String.format("copy properties from %s to %s failed", source, target);
            log.error(msg, ex);
            throw new BusinessException("500", msg);
        }
    }

}
