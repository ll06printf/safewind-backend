package com.safewind.infra.security.aspect;

import com.safewind.common.entity.BaseEntity;
import com.safewind.infra.security.service.SecurityUtil;
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

        for (Object o:args){
            if(o instanceof BaseEntity baseEntity){
                // 获取登录用户
                String loginUser = SecurityUtil.getLoginUser().getUserId().toString();
                if(methodName.startsWith("add")||methodName.startsWith("insert")){
                    baseEntity.setCreateBy(loginUser);
                    baseEntity.setCreateTime(LocalDateTime.now());
                    baseEntity.setUpdateTime(LocalDateTime.now());
                    baseEntity.setDelFlag("0");
                    baseEntity.setUpdateBy(loginUser);
                }
                else if(methodName.startsWith("update")||methodName.startsWith("edit")){
                    baseEntity.setUpdateBy(loginUser);
                    baseEntity.setUpdateTime(LocalDateTime.now());
                }
            }
        }
    }

}
