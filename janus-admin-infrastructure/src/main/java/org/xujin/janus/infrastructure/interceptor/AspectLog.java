package org.xujin.janus.infrastructure.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.xujin.janus.infrastructure.tunnel.db.dao.AuditLogMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.AuditLogDO;
import org.xujin.janus.infrastructure.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenglong.ren
 * @date 2020/6/1 15:18
 * @desc
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class AspectLog {

    @Resource
    private AuditLogMapper auditLogMapper;

    @Pointcut(value = "execution(*  org.xujin.janus.controller..*(..))")
    private void controllerAspect() {
    }

    /**
     * 方法成功return之后的advice
     * @param joinPoint
     * @param rtv
     */
    @AfterReturning(value = "controllerAspect()", returning = "rtv")
    public void after(JoinPoint joinPoint, Object rtv) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        boolean add = joinPoint.getSignature().toString().contains("add");
        boolean update = joinPoint.getSignature().toString().contains("update");
        boolean delete = joinPoint.getSignature().toString().contains("delete");
        if (add || update || delete) {
            //当前登录用户
            String userName = SessionUtils.getUserName();

            //请求方式
            String method = request.getMethod();

            //请求路径
            String requestURL = request.getRequestURL().toString();

            //请求ip
            String ip = request.getRemoteAddr() + ":" + request.getRemotePort();

            //操作类型   增加  删除  修改
            String type = "";
            if (add) {
                type = "新增";
            } else if (update) {
                type = "修改";
            } else {
                type = "删除";
            }
            //请求参数
            String requestArgs = "null";
            if (null != joinPoint.getArgs()) {
                requestArgs = JSONObject.toJSONString(joinPoint.getArgs());
            }

            //处理结果
            String result = rtv.toString();
            AuditLogDO auditLogDO = new AuditLogDO();
            auditLogDO.setUsername(userName);
            auditLogDO.setMethod(method);
            auditLogDO.setUrl(requestURL);
            auditLogDO.setIp(ip);
            auditLogDO.setType(type);
            auditLogDO.setParams(requestArgs);
            auditLogDO.setDealResult(result);
            auditLogMapper.insert(auditLogDO);
        }
    }

}
