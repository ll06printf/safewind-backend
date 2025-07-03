package com.safewind.domain.service.impl;

import com.safewind.common.enums.RoleExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.Page;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.domain.bo.*;
import com.safewind.domain.converter.RoleDomainConverter;
import com.safewind.domain.service.RoleDomainService;
import com.safewind.infra.basic.entity.RoleUser;
import com.safewind.infra.basic.entity.SysRole;
import com.safewind.infra.basic.entity.SysRoleMenu;
import com.safewind.infra.basic.entity.SysUserRole;
import com.safewind.infra.basic.service.SysRoleMenuService;
import com.safewind.infra.basic.service.SysRoleService;
import com.safewind.infra.basic.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Objects;
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
                    return insertBatch(roleBO, sysRole.getRoleId());
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
                return insertBatch(roleBO, roleBO.getRoleId());
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
                // 删除角色
                boolean b = sysRoleService.deleteById(roleId);
                List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.queryById(roleId);
                if (!sysRoleMenus.isEmpty()) {
                    return sysRoleMenuService.deleteById(roleId);
                }
                return true;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("删除角色失败", e);
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
     * @param: roleBO
     * @param: roleId
     * @return boolean
     * @author Darven
     * @date 2025/7/1 21:47
     * @description: 批量插入角色菜单关系
     */
    private boolean insertBatch(RoleBO roleBO,Long roleId){
        List<Long> menuIds = roleBO.getMenuIds();
        if(menuIds==null){
            throw new BizException(RoleExceptionEnum.MENU_ID_LIST_NULL);
        }
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
        List<Long> userIds = roleUserBO.getUserId();
        List<SysUserRole> sysUserRoleList = userIds.stream().map(userId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleUserBO.getRoleId());
            return sysUserRole;
        }).collect(Collectors.toList());
        return sysUserRoleService.insertBatch(sysUserRoleList)>0;
    }
}
