package com.safewind.infra.basic.service;

import com.safewind.common.page.Page;
import com.safewind.infra.basic.entity.SysRole;
import com.safewind.infra.basic.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface SysRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysRole queryById(Long roleId);


    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    SysRole insert(SysRole sysRole);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    SysRole update(SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

    /**
     * 查询多条数据
     *
     * @param sysRole 查询条件
     * @param page 分页对象
     * @return 对象列表
     */
    List<SysRole> queryRole(SysRole sysRole, Page page);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    long count();
}
