package com.safewind.infra.security.aspect;

import com.safewind.common.entity.BaseEntity;
import com.safewind.infra.security.entity.LoginUser;
import com.safewind.infra.security.service.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  21:03
 * @Description: 基类字段插入（切面实现）
 */
@Slf4j
@Aspect
@Component
public class EntityFillAspect {

    /**
    * @Description:
    * @return void
    * @author Darven
    * @Date 2025/6/26
    */
    @Pointcut("@annotation(com.safewind.common.annotation.EntityFill)")
    public void pointCut(){
    }

    /**
    * @Description:
    * @param joinPoint 参数
    * @return void 返回值
    * @author Darven
    * @Date 2025/6/26
    */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        //
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = signature.getMethod();
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();
        String loginUser;
        try {
            loginUser = SecurityUtil.getLoginUser().getUserId().toString();
        } catch (Exception e) {
            loginUser = "anonymous";
            log.warn("无法获取当前登录用户");
        }
        for (Object o:args){
            if(o instanceof BaseEntity baseEntity){
                // 如果是单体
                fillEntity(baseEntity,loginUser,methodName);
            } else if (o instanceof Iterable<?> iterable) {
                // 如果是列表的话
                for(Object o1:iterable){
                    if(o1 instanceof BaseEntity baseEntity){
                        fillEntity(baseEntity,loginUser,methodName);
                    }
                }
            }
        }
    }

    /**
     * 填充 BaseEntity 的公共字段
     */
    private void fillEntity(BaseEntity entity, String loginUser, String methodName) {
        if (methodName.startsWith("add") || methodName.startsWith("insert")) {
            entity.setCreateBy(loginUser);
            entity.setCreateTime(LocalDateTime.now());
            entity.setUpdateTime(LocalDateTime.now());
            entity.setDelFlag("0");
            entity.setUpdateBy(loginUser);
        } else if (methodName.startsWith("update") || methodName.startsWith("edit")) {
            entity.setUpdateBy(loginUser);
            entity.setUpdateTime(LocalDateTime.now());
        }
    }

}
