package com.safewind.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;


/**
 * @Author: Darven
 * @CreateTime: 2025-05-29  20:01
 * @Description: 领域用户信息
 */
@Data
@Builder
public class UserBO {
    /**
     * 用户id
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
     * 用户信息表
     * */
    private UserInfoBO userInfo;

    /**
     * 用户角色
     * */
    private List<UserRoleBO> roles;

    /**
     * 角色键列表
     */
    private Set<String> roleKeys;

    /**
     * 部门
     * */
    private UserDeptBO dept;

    /**
     * 权限
     * */
    private Set<String> permissions;

    /**
     * 是否是管理员
     * */
    private Boolean isAdmin;
}
