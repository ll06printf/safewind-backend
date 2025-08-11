package com.safewind.domain.service;

import com.safewind.domain.bo.MenuListBO;
import com.safewind.domain.bo.UserBO;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-29  20:00
 * @Description: 用户业务模块
 */
public interface UserDomainService {
    /**
     * 查询用户信息（用户中心）
     */
    UserBO getUserInfo();

    /**
     * 查询用户路由
     */
    List<MenuListBO> getRoutes(Long userId);
}
