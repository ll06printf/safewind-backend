package com.safewind.application.controller.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-29  19:56
 * @Description: 用户信息
 */
@Data
@Builder
public class UserVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录用户名，学号
     */
    private String studentId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户信息表 - 对应 UserInfoBO
     */
    private UserInfoVO userInfo;

    /**
     * 用户角色列表 - 对应 List<UserRoleBO>，支持多角色
     */
    private List<UserRoleVO> roles;

    /**
     * 角色键列表
     */
    private Set<String> roleKeys;

    /**
     * 部门信息 - 对应 UserDeptBO
     */
    private UserDeptVO dept;

    /**
     * 权限列表 - 对应 Set<String> permissions
     */
    private Set<String> permissions;

    /**
     * 是否是管理员
     * */
    private Boolean isAdmin;
}
