package com.safewind.infra.security.service;

import com.safewind.infra.basic.entity.SysRole;
import com.safewind.infra.basic.entity.SysUser;
import com.safewind.infra.basic.service.SysRoleService;
import com.safewind.infra.basic.service.impl.SysMenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Darven
 * @createTime: 2025-08-09  00:06
 * @description: 用户权限处理
 */
@Component
public class SysPermissionService {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuServiceImpl sysMenuService;

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            // 修改：支持多角色
            if (!CollectionUtils.isEmpty(user.getRoles())) {
                for (SysRole role : user.getRoles()) {
                    if (role != null && role.getRoleKey() != null) {
                        roles.add(role.getRoleKey());
                    }
                }
            } else {
                // 兼容旧代码：从数据库查询
                roles.addAll(sysRoleService.selectRolePermissionByUserId(user.getId()));
            }
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            // 修改：支持多角色
            if (!CollectionUtils.isEmpty(user.getRoles()))
            {
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (SysRole role : user.getRoles())
                {
                    if (role != null && "0".equals(role.getStatus()))
                    {
                         // 这里需要调用菜单服务获取角色权限
                         Set<String> rolePerms = sysMenuService.selectMenuPermsByRoleId(role.getRoleId());
                         perms.addAll(rolePerms);
                    }
                }
            }
        }
        return perms;
    }
}
