package com.safewind.infra.security.service;

import com.safewind.common.enums.ResultCodeEnum;
import com.safewind.common.exception.BizException;
import com.safewind.infra.security.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-27  22:37
 * @Description: 安全校验工具类
 */
public class SecurityUtil {

    /**
     * 获取用户id
     * */
    public static Long getUserId(){
        LoginUser loginUser = getLoginUser();
        return loginUser.getUserId();
    }

    /**
     * 获取用户信息
     * */
    public static LoginUser getLoginUser(){
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new BizException(ResultCodeEnum.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
    * @Description
    * @return java.lang.Boolean
    * @author Darven
    * @Date 2025/6/26
    */
    public static Set<String> getAuth(){
        try
        {
            LoginUser loginUser = (LoginUser) getAuthentication().getPrincipal();
            return loginUser.getPermissions();
        }
        catch (Exception e)
        {
            throw new BizException(ResultCodeEnum.UNAUTHORIZED);
        }
    }

    /**
    * @Description 是否为超级管理员
    * @param userId 用户id
    * @return java.lang.Boolean
    * @author Darven
    * @Date 2025/6/26
    */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
