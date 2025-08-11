package com.safewind.infra.basic.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户名(SysUser)实体类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -56137085959451306L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 登录用户名，学号
     */
    private String studentId;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信唯一id
     * */
    private String openid;

    /**
     * 用户信息表
     * */
    private SysUserInfo userInfo;

    /**
     * 用户角色列表 - 修改为支持多角色
     * */
    private List<SysRole> roles;

    /**
     * 部门
     * */
    private SysDept dept;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 判断是否是超级管理员
     * */
    public boolean isAdmin() {
        return this.id != null && this.id.equals(1L);
    }

    /**
     * 获取用户的主要角色
     * */
    public SysRole getRole() {
        if (roles != null && !roles.isEmpty()) {
            return roles.get(0);
        }
        return null;
    }

    /**
     * 设置用户的主要角色
     * */
    public void setRole(SysRole role) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        if (!this.roles.isEmpty()) {
            this.roles.set(0, role);
        } else {
            this.roles.add(role);
        }
    }

}

