package com.safewind.infra.security.aspect;

import com.safewind.common.annotation.Anonymous;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author: Darven
 * @createTime: 2025-08-16  01:50
 * @description: 获取匿名访问注解的url
 */
@Configuration
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    // 使用 Lombok 的 @Getter 注解自动生成 getter 方法
    @Getter
    private List<String> permitAllUrl=new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        // 获取 Spring MVC 的请求映射处理器映射器实例
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取所有的请求映射信息和对应的处理方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        // 遍历所有请求映射信息
        handlerMethods.keySet().forEach(info -> {

            HandlerMethod handlerMethod = handlerMethods.get(info);
            // 获取方法上的 @Anonymous 注解
            Anonymous methodAnonymous = AnnotationUtils.findAnnotation(handlerMethod.getMethod(),Anonymous.class);
            Optional.ofNullable(methodAnonymous).ifPresent(anonymous -> {
                // 如果方法上有 @Anonymous 注解，则将该请求路径添加到允许匿名访问的列表中
                // 兼容新旧版本Spring，处理可能为null的PatternsCondition
                Set<String> patterns = getPatterns(info);
                if (patterns != null) {
                    permitAllUrl.addAll(patterns);
                }
            });
            // 获取类上的 @Anonymous 注解
            Anonymous classAnonymous = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(),Anonymous.class);
            Optional.ofNullable(classAnonymous).ifPresent(anonymous -> {
                // 如果类上有 @Anonymous 注解，则将该请求路径添加到允许匿名访问的列表中
                // 兼容新旧版本Spring，处理可能为null的PatternsCondition
                Set<String> patterns = getPatterns(info);
                if (patterns != null) {
                    permitAllUrl.addAll(patterns);
                }
            });

        });
    }

    /**
     * 兼容新旧版本Spring的获取路径模式方法
     * 在Spring 5.3+版本中，getPatternsCondition()可能返回null
     * @param info RequestMappingInfo对象
     * @return 路径模式集合
     */
    private Set<String> getPatterns(RequestMappingInfo info) {
        // 尝试使用新版本API
        if (info.getPathPatternsCondition() != null) {
            return info.getPathPatternsCondition().getPatternValues();
        }
        // 回退到旧版本API
        else if (info.getPatternsCondition() != null) {
            return info.getPatternsCondition().getPatterns();
        }
        // 如果都为null，返回空集合而不是null
        return Set.of();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 设置应用上下文
        this.applicationContext=applicationContext;
    }

}
