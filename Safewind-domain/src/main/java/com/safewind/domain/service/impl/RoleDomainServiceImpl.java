package com.safewind.domain.service.impl;

import com.safewind.common.enums.RoleExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.Page;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.*;
import com.safewind.domain.converter.RoleDomainConverter;
import com.safewind.domain.service.RoleDomainService;
import com.safewind.infra.basic.entity.*;
import com.safewind.infra.basic.service.SysMenuService;
import com.safewind.infra.basic.service.SysRoleMenuService;
import com.safewind.infra.basic.service.SysRoleService;
import com.safewind.infra.basic.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: Darven
 * @createTime: 2025-07-01  10:51
 * @description: 角色领域服务实现类
 */
@Slf4j
@Service
public class RoleDomainServiceImpl implements RoleDomainService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * @return PageResult<RoleListBO>
     * @param: roleBO
     * @author Darven
     * @date 2025/7/1 11:06
     * @description: 查询角色列表
     */
    @Override
    public PageResult<RoleListBO> queryRole(RoleBO roleBO) {
        // 类型转换
        SysRole sysRole = RoleDomainConverter.INSTANCE.roleBOToEntity(roleBO);
        // 查询
        Page page = new Page();
        page.setPageSize(roleBO.getPageSize());
        page.setPageNum(roleBO.getPageNum());
        List<SysRole> sysRoleList = sysRoleService.queryRole(sysRole, page);
        // 查询总数
        long total = sysRoleService.count(sysRole);
        // 转换
        Long totalPage = PageUtils.getTotalPage(total, page.getPageSize());
        List<RoleListBO> roleListBOS = RoleDomainConverter.INSTANCE.entityToListBO(sysRoleList);
        return PageResult.<RoleListBO>builder()
                .pageNum(roleBO.getPageNum())
                .pageSize(roleBO.getPageSize())
                .totalSize(total)
                .totalPages(totalPage)
                .data(roleListBOS)
                .build();
    }

    /**
     * @param: roleBO
     * @return Boolean
     * @author Darven
     * @date 2025/7/1 13:47
     * @description: 添加角色
     */
    @Override
    public Boolean addRole(RoleBO roleBO) {
        // 实体转换
        SysRole sysRole = RoleDomainConverter.INSTANCE.roleBOToEntity(roleBO);
        // 检查是否存在
        SysRole query = sysRoleService.queryById(sysRole.getRoleId());
        if(Objects.nonNull(query)){
            throw new BizException(RoleExceptionEnum.ROLE_EXIST);
        }
        return transactionTemplate.execute(status -> {
            try{
                // 添加
                SysRole insert = sysRoleService.insert(sysRole);
                // 添加角色菜单关系
                if(Objects.nonNull(insert)){
                    return insertMenuBatch(roleBO, sysRole.getRoleId());
                }
                return false;
            }catch(Exception e){
                status.setRollbackOnly();
                log.error("添加失败={}",e.getMessage(), e);
                throw new BizException(RoleExceptionEnum.ROLE_ADD_ERROR);
            }
        });
    }

    /**
     * @param: roleBO
     * @return Boolean
     * @author Darven
     * @date 2025/7/1 13:47
     * @description: 修改角色
     */
    @Override
    public Boolean updateRole(RoleBO roleBO) {
        // 实体转化
        SysRole sysRole = RoleDomainConverter.INSTANCE.roleBOToEntity(roleBO);
        // 查询是否存在
        SysRole role = sysRoleService.queryById(roleBO.getRoleId());
        if (Objects.isNull(role)) {
            throw new BizException(RoleExceptionEnum.ROLE_IS_NULL);
        }

        return transactionTemplate.execute(status -> {
            try {
                // 修改角色
                SysRole update = sysRoleService.update(sysRole);
                // 查询列表
                List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.queryById(roleBO.getRoleId());
                if(!sysRoleMenus.isEmpty()){
                    // 删除角色权限
                    boolean delete = sysRoleMenuService.deleteById(roleBO.getRoleId());
                }
                return insertMenuBatch(roleBO, roleBO.getRoleId());
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("修改角色异常: {}", e.getMessage(), e);
                throw new BizException(RoleExceptionEnum.ROLE_UPDATE_ERROR);
            }
        });
    }

    /**
     * @param: roleId
     * @return boolean
     * @author Darven
     * @date 2025/7/1 21:48
     * @description: 删除角色
     */
    @Override
    public boolean deleteRole(Long roleId) {
        SysRole sysRole = sysRoleService.queryById(roleId);
        if (Objects.isNull(sysRole)) {
            throw new BizException(RoleExceptionEnum.ROLE_IS_NULL);
        }
        // 删除角色绑定的菜单权限
        Boolean result = transactionTemplate.execute(status -> {
            try {
                // 如果有菜单绑定，不给删除
                List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.queryById(roleId);
                if (!sysRoleMenus.isEmpty()) {
                    throw new BizException(RoleExceptionEnum.ROLE_HAS_MENU);
                }
                // 如果有用户绑定，不给删除
                List<SysUserRole> sysUserRoleList= sysUserRoleService.queryByRoleId(roleId);
                if(!sysUserRoleList.isEmpty()){
                    throw new BizException(RoleExceptionEnum.ROLE_HAS_MENU);
                }
                // 删除角色
                boolean b = sysRoleService.deleteById(roleId);
                return true;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("删除角色失败", e);
                // 显示内部自定义错误，避免被覆盖
                if(e instanceof BizException){
                    throw (BizException) e;
                }
                throw new BizException(RoleExceptionEnum.ROLE_DELETE_ERROR);
            }
        });
        return Boolean.TRUE.equals(result); // 安全处理 null 和 false
    }

    /**
     * @param: roleUserBO
     * @return boolean
     * @author Darven
     * @date 2025/7/2 18:01
     * @description: 分配用户
     */
    @Override
    public boolean distributionRole(RoleUserBO roleUserBO) {
        Long roleId = roleUserBO.getRoleId();
        Boolean execute = transactionTemplate.execute(status -> {
            try {
                // 查询角色是否存在
                SysRole sysRole = sysRoleService.queryById(roleId);
                if (Objects.isNull(sysRole)) {
                    throw new BizException(RoleExceptionEnum.ROLE_IS_NULL);
                }
                // 删除已有的角色用户信息
                sysUserRoleService.deleteById(roleId);
                // 批量插入角色用户信息
                return insertBatchUserRole(roleUserBO);
            } catch (Exception e) {
                // 事务回滚
                status.setRollbackOnly();
                log.info("批量插入角色用户信息异常", e);
                throw new BizException(RoleExceptionEnum.ROLE_USER_ADD_ERROR);
            }
        });
        return Boolean.TRUE.equals(execute);
    }

    /**
     * @param: roleUserQueryBO
     * @return PageResult<RoleUserListBO>
     * @author Darven
     * @date 2025/7/2 18:01
     * @description: 查询未分配的角色列表
     */
    @Override
    public PageResult<RoleUserListBO> queryUnDistributionRole(RoleUserQueryBO roleUserQueryBO) {
        // 实体转换
        RoleUser roleUser = RoleDomainConverter.INSTANCE.roleUserQueryBOToEntity(roleUserQueryBO);
        // 查询
        List<RoleUser> roleUserList=sysUserRoleService.queryUnDistributionRole(roleUser);
        // 实体转化
        List<RoleUserListBO> roleUserListBOS = RoleDomainConverter.INSTANCE.roleUserListToListBO(roleUserList);
        // 查询总条数  todo 这里感觉需要优化
        long count = sysUserRoleService.queryUnDistributionRoleCount(roleUser);
        // 封装分页结果
        return PageResult.<RoleUserListBO>builder()
                .pageNum(roleUserQueryBO.getPageNum())
                .pageSize(roleUserQueryBO.getPageSize())
                .totalSize(count)
                .totalPages(PageUtils.getTotalPage(count, roleUserQueryBO.getPageSize()))
                .data(roleUserListBOS)
                .build();
    }

    /**
     * @param: roleUserQueryBO
     * @return PageResult<RoleUserListBO>
     * @author Darven
     * @date 2025/7/2 18:01
     * @description: 查询已分配的角色列表
     */
    @Override
    public PageResult<RoleUserListBO> queryDistributionRole(RoleUserQueryBO roleUserQueryBO) {
        // 实体转换
        RoleUser roleUser = RoleDomainConverter.INSTANCE.roleUserQueryBOToEntity(roleUserQueryBO);
        // 查询当前角色分配的用户
        List<RoleUser> roleUserList=sysUserRoleService.queryDistributionRole(roleUser);
        // 实体转化
        List<RoleUserListBO> roleUserListBOS = RoleDomainConverter.INSTANCE.roleUserListToListBO(roleUserList);
        // 查询总条数  todo 这里感觉需要优化  fix：可以直接全部查出，然后在进行分页，不使用数据库分页
        long count = sysUserRoleService.queryDistributionRoleCount(roleUser);
        // 封装分页结果
        return PageResult.<RoleUserListBO>builder()
                .pageNum(roleUserQueryBO.getPageNum())
                .pageSize(roleUserQueryBO.getPageSize())
                .totalSize(count)
                .totalPages(PageUtils.getTotalPage(count, roleUserQueryBO.getPageSize()))
                .data(roleUserListBOS)
                .build();
    }

    /**
     * @return Result<Boolean>
     * @param: roleUserBO
     * @author Darven
     * @date 2025/7/2 18:01
     * @description: 批量取消授权用户
     */
    @Override
    public boolean batchCancelAuthorizeUser(RoleUserBO roleUserBO) {
        // 直接数据库删除就行
        return deleteRoleUserBatch(roleUserBO);
    }

    @Override
    public boolean distributionSingleRole(RoleUserBO roleUserBO) {
        Long roleId = roleUserBO.getRoleId();
        Boolean execute = transactionTemplate.execute(status -> {
            try {
                // 查询角色是否存在
                SysRole sysRole = sysRoleService.queryById(roleId);
                if (Objects.isNull(sysRole)) {
                    throw new BizException(RoleExceptionEnum.ROLE_IS_NULL);
                }
                // 查询该用户是否存在
                existRoleUser(roleUserBO);
                // 批量插入角色用户信息,todo:这里直接使用原来的逻辑，只要校对传参是一个就行
                return insertBatchUserRole(roleUserBO);
            } catch (Exception e) {
                // 事务回滚
                status.setRollbackOnly();
                log.info("批量插入角色用户信息异常", e);
                throw new BizException(RoleExceptionEnum.ROLE_USER_ADD_ERROR);
            }
        });
        return Boolean.TRUE.equals(execute);
    }


    /**
     * @param: roleBO
     * @param: roleId
     * @return boolean
     * @author Darven
     * @date 2025/7/1 21:47
     * @description: 批量插入角色菜单关系
     */
    private boolean insertMenuBatch(RoleBO roleBO,Long roleId){
        List<Long> menuIds = roleBO.getMenuIds();
        // 菜单ID为空
        if(menuIds==null){
            throw new BizException(RoleExceptionEnum.MENU_ID_LIST_NULL);
        }
        // 菜单ID为空
        if(roleBO.getMenuIds().isEmpty()){
            return true;
        }
        List<SysRoleMenu> sysRoleMenus = menuIds.stream().map(menuId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            return sysRoleMenu;
        }).collect(Collectors.toList());
        return this.sysRoleMenuService.insertBatch(sysRoleMenus);
    }

    /**
     * 批量插入用户角色关系
     * @param roleUserBO 角色用户绑定
     * @return  boolean
     */
    private boolean insertBatchUserRole(RoleUserBO roleUserBO) {
        List<Long> userIds = roleUserBO.getUserIds();
        List<SysUserRole> sysUserRoleList = userIds.stream().map(userId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleUserBO.getRoleId());
            return sysUserRole;
        }).collect(Collectors.toList());
        return sysUserRoleService.insertBatch(sysUserRoleList)>0;
    }

    /**
     * 批量删除用户角色关系
     * @param roleUserBO 角色用户绑定
     */
    private boolean deleteRoleUserBatch(RoleUserBO roleUserBO) {
        return sysUserRoleService.deleteByRoleUserId(roleUserBO.getRoleId(),roleUserBO.getUserIds());
    }

    /**
     * 判断该用户是否已分配角色
     * @param roleUserBO 角色用户绑定
     */
    private void existRoleUser(RoleUserBO roleUserBO) {
        RoleUser roleUser = new RoleUser();
        if(roleUserBO.getUserIds()==null||roleUserBO.getUserIds().size()>1){
            throw new BizException(RoleExceptionEnum.USER_ID_LIST_ERROR);
        }
        roleUser.setUserId(roleUserBO.getUserIds().get(0));
        roleUser.setRoleId(roleUserBO.getRoleId());
        List<RoleUser> roleUserList = sysUserRoleService.queryDistributionRole(roleUser);
        if(!roleUserList.isEmpty()&&roleUserList.size()!=1){
            throw new BizException(RoleExceptionEnum.USER_ROLE_EXIST);
        }
    }

    /**
     * 查询角色已分配的菜单权限
     */
    @Override
    public List<RoleMenuListBO> queryRoleMenus(Long roleId) {
        // 查询角色是否存在
        SysRole sysRole = sysRoleService.queryById(roleId);
        if (Objects.isNull(sysRole)) {
            throw new BizException(RoleExceptionEnum.ROLE_IS_NULL);
        }

        // 查询角色已分配的菜单
        List<SysMenu> menuList = sysMenuService.queryByRole(roleId);

        // 转换为BO
        return menuList.stream()
                .map(menu -> {
                    RoleMenuListBO bo = new RoleMenuListBO();
                    bo.setMenuId(menu.getMenuId());
                    bo.setMenuName(menu.getMenuName());
                    bo.setParentId(menu.getParentId());
                    bo.setOrderNum(menu.getOrderNum());
                    bo.setPath(menu.getPath());
                    bo.setComponent(menu.getComponent());
                    bo.setQuery(menu.getQuery());
                    bo.setRouteName(menu.getRouteName());
                    bo.setIsFrame(menu.getIsFrame());
                    bo.setIsCache(menu.getIsCache());
                    bo.setMenuType(menu.getMenuType());
                    bo.setVisible(menu.getVisible());
                    bo.setStatus(menu.getStatus());
                    bo.setPerms(menu.getPerms());
                    bo.setIcon(menu.getIcon());
                    bo.setIsAssigned(true);
                    return bo;
                })
                .collect(Collectors.toList());
    }

    /**
     * 分配菜单权限给角色
     */
    /**
     * 分配菜单权限给角色
     */
    @Override
    public boolean assignMenusToRole(RoleMenuBO roleMenuBO) {
        Long roleId = roleMenuBO.getRoleId();
        List<Long> menuIds = roleMenuBO.getMenuIds();

        // 严格参数验证
        if (roleId == null) {
            throw new BizException(RoleExceptionEnum.ID_NOT_NULL);
        }

        // 不允许 menuIds 为 null
        if (menuIds == null) {
            throw new BizException(RoleExceptionEnum.MENU_ID_LIST_NULL);
        }

        // 查询角色是否存在
        SysRole sysRole = sysRoleService.queryById(roleId);
        if (Objects.isNull(sysRole)) {
            throw new BizException(RoleExceptionEnum.ROLE_IS_NULL);
        }

        // 验证菜单是否存在（即使列表为空也要验证）
        if (!menuIds.isEmpty()) {
            List<SysMenu> menuList = sysMenuService.queryByIds(menuIds);
            if (menuList.size() != menuIds.size()) {
                throw new BizException(RoleExceptionEnum.MENU_ID_LIST_ERROR);
            }
        }

        return Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            try {
                // 删除角色原有的菜单权限
                sysRoleMenuService.deleteById(roleId);

                // 如果菜单ID列表为空，表示清空该角色的所有菜单权限
                if (menuIds.isEmpty()) {
                    log.info("清空角色 {} 的所有菜单权限", roleId);
                    return true;
                }

                // 批量插入新的角色菜单关系
                List<SysRoleMenu> roleMenuList = menuIds.stream()
                        .map(menuId -> {
                            SysRoleMenu roleMenu = new SysRoleMenu();
                            roleMenu.setRoleId(roleId);
                            roleMenu.setMenuId(menuId);
                            return roleMenu;
                        })
                        .collect(Collectors.toList());

                boolean result = sysRoleMenuService.insertBatch(roleMenuList);
                log.info("角色 {} 分配菜单权限成功，菜单数量：{}", roleId, menuIds.size());
                return result;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("分配菜单权限失败，角色ID：{}", roleId, e);
                throw new BizException(RoleExceptionEnum.ROLE_DISTRIBUTION_ERROR);
            }
        }));
    }

    /**
     * 查询所有菜单（包含是否已分配给角色的标识）
     */
    @Override
    public List<RoleMenuListBO> queryAllMenusWithRoleStatus(Long roleId) {
        // 查询所有菜单
        List<SysMenu> allMenus = sysMenuService.query(new SysMenu());

        // 查询角色已分配的菜单ID
        List<SysRoleMenu> assignedMenus = sysRoleMenuService.queryById(roleId);
        Set<Long> assignedMenuIds = assignedMenus.stream()
                .map(SysRoleMenu::getMenuId)
                .collect(Collectors.toSet());

        // 转换为BO并标记是否已分配
        return allMenus.stream()
                .map(menu -> {
                    RoleMenuListBO bo = new RoleMenuListBO();
                    bo.setMenuId(menu.getMenuId());
                    bo.setMenuName(menu.getMenuName());
                    bo.setParentId(menu.getParentId());
                    bo.setOrderNum(menu.getOrderNum());
                    bo.setPath(menu.getPath());
                    bo.setComponent(menu.getComponent());
                    bo.setQuery(menu.getQuery());
                    bo.setRouteName(menu.getRouteName());
                    bo.setIsFrame(menu.getIsFrame());
                    bo.setIsCache(menu.getIsCache());
                    bo.setMenuType(menu.getMenuType());
                    bo.setVisible(menu.getVisible());
                    bo.setStatus(menu.getStatus());
                    bo.setPerms(menu.getPerms());
                    bo.setIcon(menu.getIcon());
                    bo.setIsAssigned(assignedMenuIds.contains(menu.getMenuId()));
                    return bo;
                })
                .collect(Collectors.toList());
    }

}
