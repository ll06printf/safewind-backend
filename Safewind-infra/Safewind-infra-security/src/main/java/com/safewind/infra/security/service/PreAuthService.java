package com.safewind.infra.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  19:59
 * @Description: 配合security使用
 */
@Service("ss")
public class PreAuthService {

    /**
    * @Description: 校验用户权限
    * @param permission 参数
    * @return java.lang.Boolean 返回值
    * @author Darven
    * @Date 2025/6/26
    */
    public Boolean preAuth(String permission){
        // 获取上下文权限
        Set<String> auth = SecurityUtil.getAuth();
        return auth.contains(permission);
    }
}
