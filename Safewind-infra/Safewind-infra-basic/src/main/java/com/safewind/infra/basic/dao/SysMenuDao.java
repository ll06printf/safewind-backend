package com.safewind.infra.basic.dao;

import com.safewind.common.page.Page;
import com.safewind.infra.basic.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author Darven
 * @since 2025-05-21 21:46:54
 */
public interface SysMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    SysMenu queryById(Long menuId);


    /**
     * 统计总行数
     *
     * @param sysMenu 查询条件
     * @return 总行数
     */
    long count(SysMenu sysMenu);

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 影响行数
     */
    int insert(SysMenu sysMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysMenu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysMenu> entities);

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 影响行数
     */
    int update(SysMenu sysMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Long menuId);

    /**
     * 查询权限
     * */
    List<SysMenu> queryByRoleId(Long roleId);

    /**
     * @param: sysMenu
     * @param: page
     * @return List<SysMenu>
     * @author Darven
     * @date 2025/6/27 15:37
     * @description: 分页查询
     */
    List<SysMenu> queryAllByLimit(@Param("sysMenu") SysMenu sysMenu, @Param("page") Page page);

    /**
     * @param
     * @param sysMenu
     * @return List<SysMenu>
     * @author Darven
     * @date 2025/6/29 20:24
     * @description: 查询所有菜单列表
     */
    List<SysMenu> query(SysMenu sysMenu);
}

