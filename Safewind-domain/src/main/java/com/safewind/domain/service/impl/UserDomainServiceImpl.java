package com.safewind.domain.service.impl;

import com.safewind.domain.bo.*;
import com.safewind.domain.converter.MenuDomainConverter;
import com.safewind.domain.service.UserDomainService;
import com.safewind.infra.basic.entity.SysMenu;
import com.safewind.infra.basic.entity.SysRole;
import com.safewind.infra.basic.entity.SysUser;
import com.safewind.infra.basic.entity.SysUserInfo;
import com.safewind.infra.basic.service.SysUserService;
import com.safewind.infra.basic.service.impl.SysMenuServiceImpl;
import com.safewind.infra.basic.service.impl.SysUserRoleServiceImpl;
import com.safewind.infra.security.entity.LoginUser;
import com.safewind.infra.security.service.SecurityUtil;
import com.safewind.infra.security.service.SysPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-29  20:04
 * @Description: TODO
 */
@Service
public class UserDomainServiceImpl implements UserDomainService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuServiceImpl sysMenuService;
    @Autowired
    private SysUserRoleServiceImpl sysUserRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 查询用户信息,
     * 通过上下文获取用户信息，上下文会解析token去到缓存拿取
     *
     * @return 实例对象
     */
    @Override
    public UserBO getUserInfo() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        SysUser user = loginUser.getUser();

        // 部门信息
        UserDeptBO userDeptBO = UserDeptBO.builder()
                .deptId(user.getDept().getId())
                .name(user.getDept().getName())
                .build();

        // 获取用户角色列表
        List<UserRoleBO> roles = new ArrayList<>();
        for (SysRole role : user.getRoles()) {
            UserRoleBO userRoleBO = UserRoleBO.builder()
                    .roleName(role.getRoleName())
                    .roleKey(role.getRoleKey())
                    .build();
            roles.add(userRoleBO);
        }

        // 获取角色
        Set<String> rolePermission = sysPermissionService.getRolePermission(user);

        // 获取权限
        Set<String> permissions = sysPermissionService.getMenuPermission(user);

        // 用户信息
        SysUserInfo userInfo = user.getUserInfo();
        UserInfoBO userInfoBO = UserInfoBO.builder().build();
        BeanUtils.copyProperties(userInfo, userInfoBO);

        return UserBO.builder()
                .userId(loginUser.getUserId())
                .email(user.getEmail())
                .studentId(user.getStudentId())
                .userInfo(userInfoBO)
                .dept(userDeptBO)
                .roles(roles)
                .roleKeys(rolePermission)
                .permissions(permissions)
                .isAdmin(user.isAdmin())
                .build();
    }

    @Override
    public List<MenuListBO> getRoutes(Long userId) {
        List<SysMenu> menus = null;
        if (SecurityUtil.isAdmin(userId))
        {
            menus = sysMenuService.selectMenuTreeAll();
        }
        else
        {
            menus = sysMenuService.selectMenuTreeByUserId(userId);
        }
        // 转化成MenuListBO
        List<MenuListBO> menuListBOList = MenuDomainConverter.INSTANCE.entityToListBO(menus);
        return getChildPerms(menuListBOList, 0);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<MenuListBO> getChildPerms(List<MenuListBO> list, int parentId)
    {
        List<MenuListBO> returnList = new ArrayList<>();
        for (Iterator<MenuListBO> iterator = list.iterator(); iterator.hasNext();)
        {
            MenuListBO t = (MenuListBO) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list 分类表
     * @param t 子节点
     */
    private void recursionFn(List<MenuListBO> list, MenuListBO t)
    {
        // 得到子节点列表
        List<MenuListBO> childList = getChildList(list, t);
        t.setMenuListBOList(childList);
        for (MenuListBO tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<MenuListBO> getChildList(List<MenuListBO> list, MenuListBO t)
    {
        List<MenuListBO> tlist = new ArrayList<>();
        Iterator<MenuListBO> it = list.iterator();
        while (it.hasNext())
        {
            MenuListBO n = (MenuListBO) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<MenuListBO> list, MenuListBO t)
    {
        return getChildList(list, t).size() > 0;
    }

}
