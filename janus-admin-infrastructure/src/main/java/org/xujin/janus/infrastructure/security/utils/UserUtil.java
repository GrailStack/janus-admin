package org.xujin.janus.infrastructure.security.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Optional;

/**
 * @author yage.luan
 * created at 2019/5/11 17:55
 **/
public class UserUtil {

    public static String getCurrUser() {
        return Optional.ofNullable(SecurityUtils.getSubject())
                .map(Subject::getPrincipal)
                .map(Object::toString)
                .orElse(null);
    }

}
