package com.safewind.domain.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-07-02  17:59
 * @description: 授权/未授权用户实体
 */
@Data
public class RoleUserListBO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     * */
    private Long roleId;
    /**
     * 登录用户名，学号
     */
    private String studentId;
    /**
     * 用户名
     * */
    private String userName;
    /**
     * 用户邮箱
     * */
    private String email;
    /**
     * 创建时间
     * */
    private LocalDateTime createTime;
}
