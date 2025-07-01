package com.safewind.domain.service;

import com.safewind.common.enums.RoleExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.Page;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.domain.bo.RoleBO;
import com.safewind.domain.bo.RoleListBO;
import com.safewind.domain.converter.RoleDomainConverter;
import com.safewind.infra.basic.entity.SysRole;
import com.safewind.infra.basic.entity.SysRoleMenu;
import com.safewind.infra.basic.service.SysRoleMenuService;
import com.safewind.infra.basic.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
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
        long total = sysRoleService.count();
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
                // 删除角色权限
                boolean delete = sysRoleMenuService.deleteById(roleBO.getRoleId());

                if (delete) {
                    return insertBatch(roleBO, roleBO.getRoleId());
                }
                return false;
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

                if (b) {
                    return sysRoleMenuService.deleteById(roleId);
                }
                return false;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("删除角色失败", e);
                throw new BizException(RoleExceptionEnum.ROLE_DELETE_ERROR);
            }
        });
        return Boolean.TRUE.equals(result); // 安全处理 null 和 false
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
            return false;
        }
        List<SysRoleMenu> sysRoleMenus = menuIds.stream().map(menuId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            return sysRoleMenu;
        }).collect(Collectors.toList());
        return this.sysRoleMenuService.insertBatch(sysRoleMenus);
    }
}
