package com.safewind.application.controller.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-07-02  17:54
 * @description: 角色用户分配
 */
@Data
public class RoleUserVO{
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 登录用户名，学号
     */
    private String studentId;
    /**
     * 用户名
     * */
    private String userName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 创建时间
     * */
    private LocalDateTime createTime;
}
