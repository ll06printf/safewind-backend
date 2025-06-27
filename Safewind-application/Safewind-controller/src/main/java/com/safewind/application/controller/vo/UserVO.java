package com.safewind.application.controller.vo;

import lombok.Builder;
import lombok.Data;

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

    // ========== UserInfoBO 字段 ==========
    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像（默认图片）
     */
    private String avatar;

    /**
     * 年级（如 "2025"）
     */
    private String grade;

    /**
     * 专业
     */
    private String speciality;

    /**
     * 学院
     */
    private String faculty;

    /**
     * 姓名
     */
    private String userInfoName;

    /**
     * 性别（1:男, 0:女）
     */
    private Integer sex;

    /**
     * 班级
     */
    private String className;

    // ========== UserRoleBO 字段 ==========
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    // ========== UserDeptBO 字段 ==========
    /**
     * 部门名称
     */
    private String deptName;

    // ========== 用户权限 字段 ==========
    /**
     * 拥有权限
     */
    private Set<String> permission;

}
