package com.safewind.infra.basic.entity;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-07-02  18:07
 * @description: 数据库层角色用户关系
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleUser extends Page {
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
