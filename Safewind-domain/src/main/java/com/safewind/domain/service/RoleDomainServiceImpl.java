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
import com.safewind.infra.basic.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-07-01  10:51
 * @description: 角色领域服务实现类
 */
@Service
public class RoleDomainServiceImpl implements RoleDomainService {

    @Autowired
    private SysRoleService sysRoleService;

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
        // 添加
        SysRole insert = sysRoleService.insert(sysRole);
        return Objects.nonNull(insert);
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
        if(Objects.isNull(role)){
            throw new BizException(RoleExceptionEnum.ROLE_IS_NULL);
        }
        // 修改
        SysRole update = sysRoleService.update(sysRole);
        return Objects.nonNull(update);
    }

    @Override
    public boolean deleteRole(Long roleId) {
        SysRole sysRole = sysRoleService.queryById(roleId);
        if(Objects.isNull(sysRole)){
            throw new BizException(RoleExceptionEnum.ROLE_IS_NULL);
        }
        return sysRoleService.deleteById(roleId);
    }
}
