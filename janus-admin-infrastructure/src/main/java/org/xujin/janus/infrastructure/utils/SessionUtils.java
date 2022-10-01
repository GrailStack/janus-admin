package org.xujin.janus.infrastructure.utils;

import org.xujin.halo.shiro.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


/**
 * @author chenglong.ren
 */
@Slf4j
@Component
public class SessionUtils {
    public static Integer currentUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String currentUserIdStr = request.getParameter("currentUserId");
        return Strings.isBlank(currentUserIdStr) || "null".equalsIgnoreCase(currentUserIdStr)
                ? -1 :Integer.parseInt(currentUserIdStr);
    }


    public static String getUserName() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = ((HttpServletRequest) request).getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String userName = JwtUtil.getUsername(token);
        return userName;
    }

}